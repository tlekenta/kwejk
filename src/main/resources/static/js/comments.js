$(function(){
    $('#new-comment').click(function(){
        var pCommentText = $('#comment').val();
        var pPictureId = location.pathname.split('/')[2];

        $.post('/api/picture/comments/' + pPictureId, {text: pCommentText}, function() {location.reload();});

    });
});