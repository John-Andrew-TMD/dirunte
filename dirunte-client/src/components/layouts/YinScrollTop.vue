<template>
  <div class="fix-box">
    <div class="fix-banner" v-if = "isShow">
      <i class="iconfont icon-close-square" @click="setShow(false)"></i>
      <div class="totick">专业体味问题<br/>解决方案</div>
      <div class="fix-content">
        <div>
          <i class="iconfont icon-kefu"></i>
          <div class="kufu-click mt-10">在线客服咨询</div>
        </div>
        <div>
          <i class="iconfont icon-24gf-telephone2"></i>
          <div class="phone-click mt-10">400-6000-382</div>
        </div>
      </div>
    </div>
    <div class="scroll-top" @click="returnTop">
      <div class="box-in"></div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent,ref } from "vue";

export default defineComponent({
  setup() {
    const isShow = ref(true)
    const setShow = (value)=>{
      isShow.value = value
    }
    function returnTop() {
      let timer: number = null;
      cancelAnimationFrame(timer);
      const startTime = new Date();
      const S = document.body.scrollTop || document.documentElement.scrollTop;
      const T = 500;
      timer = requestAnimationFrame(function func() {
        const diff: number = new Date().valueOf() - startTime.valueOf();
        const t = T - Math.max(0, T - diff);
        document.documentElement.scrollTop = document.body.scrollTop =
          S - (t * S) / T;
        timer = requestAnimationFrame(func);
        if (t === T) cancelAnimationFrame(timer);
      });
    }
    return { returnTop,setShow,isShow };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";
.fix-box {
  position: fixed;
  right: 10px;
  bottom: 80px;
  z-index: 100;
  background-color: transparent;
  overflow: hidden;
  padding: 10px;
  .fix-banner {
    position: relative;
    background-color: $color-white;
    margin-bottom: 14px;
    border-radius: 4px;
    font-size: 16px;
    min-width: 150px;
    box-shadow: 0 0 4px 3px rgba(0, 0, 0, 0.2);
    .icon-close-square{
      position: absolute;
      left: 0;
      top: 0;
      color: #1f8ceb;
      cursor: pointer;
    }
    .totick{
      padding: 10px;
      text-align: center;
      color: #1f8ceb;
    }
    .fix-content {
      display: flex;
      flex-direction: column;
      align-items: center;
      background-color: #1f8ceb;
      color: #fff;
      width: 100%;
      &>*{
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-bottom: 15px;
      }
      &>*:nth-child(1){
        margin-top: 15px;
      }
      .iconfont {
        font-size: 56px;
      }
    }
  }
  .scroll-top {
    position: relative;
    width: 40px;
    height: 40px;
    padding-top: 20px;
    text-align: center;
    background-color: $color-white;
    border-radius: 20%;
    box-shadow: 0 0 4px 3px rgba(0, 0, 0, 0.2);
    cursor: pointer;
    overflow: hidden;
    float: right;
    &:hover:before {
      top: 50%;
    }
    &:hover .box-in {
      visibility: hidden;
    }
    &:before {
      content: "回到顶部";
      position: absolute;
      font-weight: bold;
      font-size: small;
      width: 30px;
      top: -50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
  }

  .box-in {
    visibility: visible;
    display: inline-block;
    height: 10px;
    width: 10px;
    border: 1px solid $color-black;
    border-color: $color-black transparent transparent $color-black;
    // 按钮中间的小三角翻转
    transform: rotate(45deg) translate(-5px, -5px);
  }
}
@media screen and (max-width: $sm) {
  .fix-banner{
    display: none;
  }
}
</style>
