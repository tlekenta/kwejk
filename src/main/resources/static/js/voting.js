$(function(){
    $('.vote-button').click(function(){
        var pButtonId = $(this).attr('id');
        $.post('api/picture/points', {action: pButtonId}, function() {
            updatePoints(pButtonId)
        });
    });
});

function updatePoints(aButtonId) {
    var pPictureId = aButtonId.split('_')[1];
    $.get('api/picture/points/' + pPictureId, function(response) {
        $('#picture_' + pPictureId).html(response);
    });
}