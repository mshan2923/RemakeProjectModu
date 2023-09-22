function main() 
{
    console.log("nicks-cors-test");
    $.ajax
    ({
        url: "https://google.com",//"https://api.github.com",
        success: function(data) 
        {
            console.log(data);
        }
    });
}
