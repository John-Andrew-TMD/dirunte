"use strict";
var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
exports.__esModule = true;
exports.config = void 0;
// 根据环境引入不同配置 process.env.NODE_ENV
// let env = process.env.NODE_ENV || "development";
var env = "sit";
var configEnv = {
    dev: {
        baseApi: "http://172.19.96.100:9011",
        domain: "http://qiwei.nat300.top",
        APPID: "wwa9ab7c817c1eab5e",
        apiName: 'm-wxwork',
        proxyKey: '/api'
    },
    sit: {
        baseApi: "https://sit-wxwork.fadada.com",
        APPID: "ww8eef8f139a6c48ac",
        domain: "https://sit-wxwork.fadada.com",
        apiName: 'm-esign',
        proxyKey: '/esign-api'
    },
    uat: {
        baseApi: "https://uat-wxwork.fadada.com",
        APPID: "ww09531096b43c153a",
        domain: "https://uat-wxwork.fadada.com",
        apiName: 'm-esign',
        proxyKey: '/esign-api'
    }
};
// console.log("==================baseApi", env, configEnv[env].baseApi);
// alert("baseApi" + configEnv[env].baseApi);
exports.config = __assign({ env: "development", mock: false, title: "开发", baseUrl: "http://localhost:9018", APPSECRET: "xxx", $cdn: "https://imgs.solui.cn" }, configEnv[env]);
