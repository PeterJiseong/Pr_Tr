const kakaoLogin = document.querySelector("#kakaoAuthorize")

kakaoLogin.addEventListener('click', function kakaoAuthorize(e){
        console.log("동작")
        window.open("/member/kakaoAuthorize", "childForm", "width=570, height=350, resizable = no, scrollbars = no")

        const authorizeUrl = "/member/kakaoAuthorize";


        // fetch(authorizeUrl)
        //     .then((res)=>{
        //         console.log(res);
        //         return res.text();
        //     })
        //     .then((data)=>{
        //         console.log(data)
        //     })

})
