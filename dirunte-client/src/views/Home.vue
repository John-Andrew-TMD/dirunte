<template>
  <!--轮播图-->
  <el-carousel
    v-if="loading"
    class="swiper-container"
    height="400px"
    :interval="4000"
  >
    <el-carousel-item v-for="(item, index) in swiperList"  :key="index">
      <el-image  style="width: 100%; height: 100%" :src="attachImageUrl(item.pic)" fit="cover"></el-image>
    </el-carousel-item>
  </el-carousel>
  <!--热门歌手-->
  <play-list
    class="play-list-container"
    path="singer-detail"
    :playList="singerList"
  ></play-list>
</template>

<script lang="ts" setup>
import { ref, onMounted } from "vue";
import PlayList from "@/components/PlayList.vue";
import { NavName } from "@/enums";
import { HttpManager } from "@/api";
import mixin from "@/mixins/mixin";

const singerList = ref([]); // 歌手列表
const swiperList = ref([]); // 轮播图列表
const loading = ref(false); // 轮播图列表
const attachImageUrl = ref(HttpManager.attachImageUrl);
const { changeIndex } = mixin();
try {
  HttpManager.getAllSinger().then((res) => {
    singerList.value = (res as ResponseBody).data.sort().slice(0, 10);
  });
  HttpManager.getSwiperList().then((res) => {
    loading.value = true;
    swiperList.value = (res as ResponseBody).data;
  });
  onMounted(() => {
    changeIndex(NavName.Home);
  });
} catch (error) {
  console.error(error);
}
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

/*轮播图*/
.swiper-container {
  width: 100%;
  margin: auto;
  img {
    width: 100%;
  }
}

.swiper-container:deep(.el-carousel__indicators.el-carousel__indicators--outside) {
  display: inline-block;
  transform: translateX(30vw);
}

.el-slider__runway {
  background-color: $color-blue;
}
</style>
