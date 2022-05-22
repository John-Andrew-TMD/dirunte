export interface IConfig {
  env: string; // 开发环境
  mock?: boolean; // mock数据
  title: string; // 项目title
  baseUrl?: string; // 项目地址
  baseApi?: string; // api请求地址
  APPID?: string; // 公众号appId  一般放在服务器端
  apiName?: string; // 公众号appScript 一般放在服务器端
  $cdn: string; // cdn公共资源路径
  domain: string;
  proxyKey:string;
}

// 根据环境引入不同配置 process.env.NODE_ENV
// let env = process.env.NODE_ENV || "development";
const env = "sit";
const configEnv = {
  dev: {
    baseApi: "http://172.19.96.100:9011",
    domain: "http://qiwei.nat300.top",
    APPID: "wwa9ab7c817c1eab5e",
    apiName:'m-wxwork',
    proxyKey:'/api'
  },
  sit: {
    baseApi: "https://sit-wxwork.fadada.com",
    APPID: "ww8eef8f139a6c48ac",
    domain: "https://sit-wxwork.fadada.com",
    apiName:'m-esign',
    proxyKey:'/esign-api'
  },
  uat: {
    baseApi: "https://uat-wxwork.fadada.com",
    APPID: "ww09531096b43c153a",
    domain: "https://uat-wxwork.fadada.com",
    apiName:'m-esign',
    proxyKey:'/esign-api'
  },
};
// console.log("==================baseApi", env, configEnv[env].baseApi);
// alert("baseApi" + configEnv[env].baseApi);
export const config: IConfig = {
  env: "development",
  mock: false,
  title: "开发",
  baseUrl: "http://localhost:9018", // 项目地址
  $cdn: "https://imgs.solui.cn",
  ...configEnv[env],
};
