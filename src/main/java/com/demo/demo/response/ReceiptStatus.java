/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.demo.response;


/**
 * Enumeration of HTTP status codes.
 * <p>
 * <p>The HTTP status code series can be retrieved via {@link #series()}.
 *
 * @author Arjen Poutsma
 * @author Sebastien Deleuze
 * @author Brian Clozel
 * @see CommonStatus.Series
 * @see <a href="http://www.iana.org/assignments/http-status-codes">HTTP Status Code Registry</a>
 * @see <a href="http://en.wikipedia.org/wiki/List_of_HTTP_status_codes">List of HTTP status codes - Wikipedia</a>
 * @since 3.0
 */
public enum ReceiptStatus implements RestStatus {
    WRITE_OFF_OK(200, "冲销成功"),

    RECEIPT_EWB_NO_LENGTH(20001, "运单号只能输入1-30位数字或字母，请重新输入。"),
    
    RECEIPT_REMARK_LENGTH(20002, "备注长度不能超过100位字符，请重新输入。"),
    
    RECEIPT_TIME_LENGTH(20003, "开始日期或结束日期不能为空"),
    
    RECEIPT_CHARGE_ITEM_NULL(20004, "费用项目不能为空。"),
    
    RECEIPT_RECEIVE_COMPANY_NULL(20005, "收款公司不能为空。"),
    
    RECEIPT_PAY_RECEIVE_COMPANY_NULL(20006, "付款公司或付款网点不能为空。");
    
    private final int status;

    private final String message;

    ReceiptStatus(int value, String reasonPhrase) {
        this.status = value;
        this.message = reasonPhrase;
    }

    public int status() {
        return this.status;
    }

    public String message() {
        return this.message;
    }
}
