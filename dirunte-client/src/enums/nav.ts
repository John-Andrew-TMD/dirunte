import { RouterName } from "./router-name";

export const enum NavName {
  Home = "首页",
  ProductIntro = "产品介绍",
  UserComment = "用户评论",
  OnlineBuy = "在线订购",
  Personal = "个人主页",
  Setting = "设置",
  SignIn = "登录",
  SignUp = "注册",
  SignOut = "退出",
}

// 左侧导航栏
export const HEADERNAVLIST = [
  {
    name: NavName.Home,
    path: RouterName.Home,
  },
  {
    name: NavName.ProductIntro,
    path: RouterName.ProductIntro,
  },
  {
    name: NavName.UserComment,
    path: RouterName.UserComment,
  },
  {
    name: NavName.OnlineBuy,
    path: RouterName.OnlineBuy,
  },
];

// 右侧导航栏
export const SIGNLIST = [
  {
    name: NavName.SignIn,
    path: RouterName.SignIn,
  },
  {
    name: NavName.SignUp,
    path: RouterName.SignUp,
  },
];

// 用户下拉菜单项
export const MENULIST = [
  {
    name: NavName.Personal,
    path: RouterName.Personal,
  },
  {
    name: NavName.Setting,
    path: RouterName.Setting,
  },
  {
    name: NavName.SignOut,
    path: RouterName.SignOut,
  },
];
