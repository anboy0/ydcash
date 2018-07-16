package com.demo.demo.tools;

import org.patchca.background.BackgroundFactory;
import org.patchca.color.RandomColorFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 *
 */
public class VerifyCode {
    public static final String SESSION_VERIFY_NAME = "verify_code";
    private static final int VERIFY_CODE_LENGTH = 4;
    private static Random random = new Random();

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     */
    public static void RendorCode(HttpServletRequest request, HttpServletResponse response) {
        ConfigurableCaptchaService captchaService = null;
        captchaService = new ConfigurableCaptchaService();
        RandomWordFactory rwf = new RandomWordFactory();
        rwf.setCharacters("0123456789");
        rwf.setMinLength(VERIFY_CODE_LENGTH);
        rwf.setMaxLength(VERIFY_CODE_LENGTH);
        captchaService.setWordFactory(rwf);
        captchaService.setFontFactory(new RandomFontFactory());
        captchaService.setColorFactory(new RandomColorFactory());
        captchaService.setBackgroundFactory(new BackgroundFactory() {
            @Override
            public void fillBackground(BufferedImage bufferedImage) {
                Graphics graphics = bufferedImage.getGraphics();
                // 验证码图片的宽高
                int imgWidth = bufferedImage.getWidth();
                int imgHeight = bufferedImage.getHeight();
                // 填充为白色背景
                graphics.setColor(Color.WHITE);
                graphics.fillRect(0, 0, imgWidth, imgHeight);
                int size = imgWidth * imgHeight / 20;
                // 画100个噪点(颜色及位置随机)
                for (int i = 0; i < size; i++) {
                    // 随机颜色
                    int rInt = random.nextInt(255);
                    int gInt = random.nextInt(255);
                    int bInt = random.nextInt(255);
                    graphics.setColor(new Color(rInt, gInt, bInt));
                    // 随机位置
                    int xInt = random.nextInt(imgWidth - 3);
                    int yInt = random.nextInt(imgHeight - 2);
                    // 随机旋转角度
                    int sAngleInt = random.nextInt(360);
                    int eAngleInt = random.nextInt(360);
                    // 随机大小
                    int wInt = random.nextInt(6);
                    int hInt = random.nextInt(6);
                    graphics.fillArc(xInt, yInt, wInt, hInt, sAngleInt, eAngleInt);
                    // 画5条干扰线
                    if (i % 20 == 0) {
                        int xInt2 = random.nextInt(imgWidth);
                        int yInt2 = random.nextInt(imgHeight);
                        graphics.drawLine(xInt, yInt, xInt2, yInt2);
                    }
                }
            }
        });


        try {
            HttpSession session = request.getSession();
            OutputStream os = response.getOutputStream();
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            String code = EncoderHelper.getChallangeAndWriteImage(captchaService, "png", os);
            session.setAttribute(SESSION_VERIFY_NAME, code);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean checkVerify(HttpSession httpSession, String verifyCode) {
        if (Tools.isEmpty(verifyCode)) {
            return false;
        }
        String _verifyCode = (String) httpSession.getAttribute(SESSION_VERIFY_NAME);
        if (!Tools.isEmpty(_verifyCode)) {
            httpSession.setAttribute(SESSION_VERIFY_NAME, "");
            if (_verifyCode.equals(verifyCode)) {
                return true;
            }
        }
        return false;
    }
}
