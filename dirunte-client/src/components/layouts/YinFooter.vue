<template>
  <div class="footer-wrapper">
    <div class="w1200">
      <div class="about-us">
        <h2 class="footer-title">关于我们</h2>
        <p class="about-desc">
          答税是国内首个高端专业财税咨询服务平台，由立信合伙人创立。
        </p>
        <p class="about-desc">
          依托遍及全球的财税专家，汇聚多方社会资源，致力于提供专业、全面、权威和及时的财税资讯，以及实时的在线财税咨询。
        </p>
      </div>
      <div class="footer-t">
        <div class="footer-t__l">
          <p class="footer-title">联系我们</p>
          <div class="company-info">
            <h4 class="company-name">深圳答税科技有限公司</h4>
            <p>地址：深圳市福田区香梅路1061号中投国际商务中心</p>
            <p>邮编：518000</p>
            <p>电话：400-6000-382（工作日 9:00-18:00 ）</p>
            <p>邮箱：info@webtax.com.cn</p>
          </div>
        </div>
        <div class="footer-t__r">
          <div class="qrcode">
            <img class="qrcode-img" :src="img1" alt />
            <p class="qrcode-title">答税微信公众号</p>
          </div>
          <div class="qrcode">
            <img class="qrcode-img" :src="img2" alt />
            <p class="qrcode-title">客服"答税君"</p>
          </div>
        </div>
      </div>
    </div>
    <div class="footer-b">
      <p>
        © Copyright（C）2018 © Copyright © www.webtax.com.cn, All Rights
        Reserved.深圳答税科技有限公司
        <a
          target="_blank"
          href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=44030502003942"
          >粤公网安备 44030502003942号</a
        >
      </p>
    </div>
  </div>
</template>
<style lang="scss" scoped>
@import "@/assets/css/footer.scss";
</style>
<script lang = "ts">
import { HttpManager } from "@/api";
export default {
  name: "FooterItem",
  components: {},
  data() {
    return {
      attachImageUrl: HttpManager.attachImageUrl,
      alink:
        "http://www.beian.gov.cn/portal/registerSystemInfo?spm=5176.8142029.631162.108.e9396d3e5oBYeW&COLLCC=2716891795&recordcode=44030502003942",
    };
  },
  computed: {
    img1(){
      return require("@/assets/images/wechatPub-img@2x.png")
    },
    img2(){
      return require("@/assets/images/wechatQR-img@2x.png")
    }
  },
  watch: {},
  methods: {},
};
</script>
