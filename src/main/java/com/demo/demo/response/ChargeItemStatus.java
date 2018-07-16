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
public enum ChargeItemStatus implements RestStatus {
    CHARGE_ITEM_CODE_REPEAT(10000, "费用项目编码重复"),

    CHARGE_ITEM_NAME_LENGTH(10001, "费用项目名称只能输入1-15位字符，请重新输入。"),
    
    CHARGE_ITEM_CODE_LENGTH(10002, "费用项目编码只能输入1-4位数字，请重新输入。");
    
    private final int status;

    private final String message;

    ChargeItemStatus(int value, String reasonPhrase) {
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
