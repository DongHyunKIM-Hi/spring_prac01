$(document).ready(function(){
    getList();
});

function list_sort(text){
    alert(text)
}

function open_pop() {
    $('#myModal').show();
}

function close_pop(flag) {
    $('#myModal').hide();
}
function  sut_pop(){
    $('#now_contents').empty();
    $('#choice_contents').hide();
}
function enroll_contents() {
    let nickname= $('#nickname').val()
    if (nickname.trim() == ''){
        nickname = "신원미상"
    }
    let title  = $('#title').val()
    if  (title.trim()   == ""){
        title  ="무제"
    }
    let contents = $('#contents').val()
    if  (contents.trim()   == ""){
        contents  ="내용없음"
    }
    let data= {'title': title,'nickname': nickname, 'contents': contents}

    $.ajax({
        type: "POST",
        url: "/api/memorys",
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    });
}

function open_contents(id){
    $('#choice_contents').show();
    $.ajax({
        type: 'GET',
        url: `/api/memorys/search/${id}`,
        success: function (response) {

            let tmp  = `<h2 style="font-size: large;  margin: 30px;">${response}</h2>
                        <button onclick="sut_pop()">닫기</button>
                        <button onclick="">수정하기</button>`
            $('#now_contents').append(tmp)
        }
    })
}

function getList(){
    $.ajax({
        type: 'GET',
        url: '/api/memorys',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {

                let message = response[i];
                let id = message['id'];
                let title = message['title']
                let nickname = message['nickname'];
                let modifiedAt = message['modifiedAt'];
                modifiedAt = modifiedAt.substring(0,10) +" "+modifiedAt.substring(11,16)
                let tempHtml = `
                <tr id="list_${id}" onclick="open_contents(${id})">
                    <td class="lalign">${title}</td>
                    <td>${nickname}</td>
                    <td>110</td>
                    <td>${modifiedAt}</td>
                </tr>`;

                $('#my_list').append(tempHtml);
            }
        }
    })
}
