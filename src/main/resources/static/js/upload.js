$(function() {
    $(".artBtn").click(function(){
        $(".art").toggle();
        if($(".artBtn").text()==="Dodaj artykuł") {
            $(".artBtn").html("Usuń artykuł");
        } else {
            $(".artBtn").html("Dodaj artykuł");
        }
    });
});