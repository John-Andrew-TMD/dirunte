<template>
  <div class="play-list-container">
    <play-list :playList="data" path="singer-detail"></play-list>

    <div class="comment-content">
      <comment-components :type="2"></comment-components>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, computed } from "vue";
import YinNav from "@/components/layouts/YinNav.vue";
import PlayList from "@/components/PlayList.vue";
import { singerStyle } from "@/enums";
import { HttpManager } from "@/api";
import CommentComponents from "@/components/Comment.vue";
// data
const activeName = ref("全部歌手");
const pageSize = ref(15); // 页数
const currentPage = ref(1); // 当前页
const allPlayList = ref([]);
// computed

const data = computed(() => {
  return allPlayList.value.slice(
    (currentPage.value - 1) * pageSize.value,
    currentPage.value * pageSize.value
  );
});

// 获取所有歌手
async function getAllSinger() {
  const result = (await HttpManager.getAllSinger()) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.data;
}

getAllSinger();
</script>
<style lang="scss" scoped>
.comment-content {
  padding: 0 10%;
  padding-bottom: 20px;
}
</style>
