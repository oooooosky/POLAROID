// 사용자 (마이페이지, 로그아웃)
$(document).ready(function(){
    $('#userToggleButton').click(function(){
        $('#userToggleMenu').toggle();
    });
});

// 좋아요 누를 때 하트 색 바꾸기
let flagLike = true;
function likeCheck() {
    document.getElementById("board-detail-like-btn").src = flagLike
        ? "/image/like.png"
        : "/image/like_default.png";
    flagLike = !flagLike;
}

// board_detail 댓글 보기
let flagComment = true;
function commentCheck() {
    document.getElementById("board-detail-comment-btn").src = flagComment
        ? "/image/comment.png"
        : "/image/comment_default.png";

    document.getElementById("board-detail-comment_box").style.display = flagComment
        ? "block"
        : "none";
    flagComment = !flagComment;
}

// board_detail 본문 전체 보기
let flagMainText = true;
function viewMore() {
    document.getElementById("board-main-text").style.display = flagMainText
        ? "block"
        : "-webkit-box";
    flagMainText = !flagMainText;
}