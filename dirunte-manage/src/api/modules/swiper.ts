import { get,post } from '../request'
const SwiperManager = {
    // =======================> 轮播图 API
  // 添加轮播图
  setSwiperList: (params) => post(`swiperList/add`, params),
  // 获取全部轮播图
  getSwiperList: () => get(`swiperList`),
  // 更新轮播图信息
  updateSwiperListMsg: (params) => post(`swiperList/update`, params),
  // 删除轮播图
  deleteSwiperList: (id) => get(`swiperList/delete?id=${id}`),
}
export default SwiperManager