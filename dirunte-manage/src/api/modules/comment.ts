import { get,post } from '../request'
const CommentManager = {
    // =======================> 评论列表 API
    // 获得指定歌曲ID的评论列表
    getCommentOfSongId: (songId) => get(`comment/song/detail?songId=${songId}`),
    // 获得指定歌单ID的评论列表
    getCommentOfSongListId: (songListId) => get(`comment/songList/detail?songListId=${songListId}`),
    // 删除评论
    updateComment:(params)=>{
        return post("comment/update", params);
    },
    deleteComment: (id) => get(`comment/delete?id=${id}`),
    getAllComment: (type=-1, id=0) => {
        let url = "";
        if (type === 1) {
            url = `comment/songList/detail?songListId=${id}`;
        } else if (type === 0) {
            url = `comment/song/detail?songId=${id}`;
        } else {
            const params = new URLSearchParams();
            params.append("type", type+"");
            return post("comment/detail", params);
        }
        return get(url);
    },
}
export default CommentManager