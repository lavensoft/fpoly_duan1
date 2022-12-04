const crypto = require('crypto');
const axios = require('axios');
const Config = require('./config');

const MOMO_CONFIG = {
    MOMO: {
        PARTNER_CODE: "MOMOJEIF20200825",
        ACCESS_KEY: "GvXVOYVZfOLfRvKJ",
        SECRECT_KEY: "fR6d99e3JvdExJfwMlJe33EFprPoOL2N",
        REDIRECT_URL: "",
        IPN_URL: `${Config.HOST}/complete_order`
    }
}

class Momo {
    static async createEWallet(amount, extraData = "") {
        var requestId = MOMO_CONFIG.MOMO.PARTNER_CODE + new Date().getTime();
        var orderId = requestId;
        var orderInfo = "pay with MoMo";
        var requestType = "captureWallet"
        var extraData = extraData;
        var redirectUrl = `${MOMO_CONFIG.MOMO.REDIRECT_URL}/transaction/successful`;
        var ipnUrl = MOMO_CONFIG.MOMO.IPN_URL;

        var rawSignature = "accessKey="+MOMO_CONFIG.MOMO.ACCESS_KEY+"&amount=" + amount+"&extraData=" + extraData+"&ipnUrl=" + ipnUrl +"&orderId=" + orderId+"&orderInfo=" + orderInfo+"&partnerCode=" + MOMO_CONFIG.MOMO.PARTNER_CODE +"&redirectUrl=" + redirectUrl+"&requestId=" + requestId+"&requestType=" + requestType
        var signature = crypto.createHmac('sha256', MOMO_CONFIG.MOMO.SECRECT_KEY).update(rawSignature).digest('hex');
        
        const requestBody = JSON.stringify({
            partnerCode : MOMO_CONFIG.MOMO.PARTNER_CODE,
            accessKey : MOMO_CONFIG.MOMO.ACCESS_KEY,
            requestId : requestId,
            amount : amount,
            orderId : orderId,
            orderInfo : orderInfo,
            redirectUrl : redirectUrl,
            ipnUrl : ipnUrl,
            extraData : extraData,
            requestType : requestType,
            signature : signature,
            lang: 'en'
        });

        return axios.post('https://test-payment.momo.vn/v2/gateway/api/create', requestBody, {
            headers: {
                'Content-Type': 'application/json',
                'Content-Length': Buffer.byteLength(requestBody),
                'Accept-Encoding' : 'application/json',
            }
        });
    }
}

module.exports = Momo;