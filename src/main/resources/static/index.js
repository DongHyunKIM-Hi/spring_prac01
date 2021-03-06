$(document).ready(function(){
    getList();

});


function edit_contents(){
    $('#new_contents_form').show();
}

function add_comment_porm(){
    $('#new_comment_form').show();
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
    window.location.reload();
}
function enroll_contents() {

    let nickname= $('#nickname').text()
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

function delete_content(id){
    alert(id)
    $.ajax({
        type: "DELETE",
        url: `/api/memorys/${id}`,
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    });
}

function delete_comment(id){
    $.ajax({
        type: "DELETE",
        url: `/api/comments/${id}`,
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    });
}


function save_contents(id){
    let contents = $('#new_contents').val();
    let data = {"contents": contents}
    $.ajax({
        type: "PUT",
        url: `/api/memorys/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지 변경에 성공하였습니다.');
            window.location.reload();
        }
    });
}

function edit_comment(comment,id,content){

    $(`#${comment}`).empty()

    let tmp = `<input placeholder="${content}" id="edit"/>   <button onclick="end_edit(${id})">수정완료</button>`
    $(`#${comment}`).append(tmp)
}
function end_edit(id){
    let comment = $('#edit').val()
    if (comment.length===0){
        alert("내용을 입력해주세요")
        $('#edit').focus()
        return
    }
    let data= {"single_comment": comment}
    $.ajax({
        type: "PUT",
        url: `/api/comments/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지 변경에 성공하였습니다.');
            window.location.reload();
        }
    });
}

function save_comment(id){

    let comment = $('#new_comment').val();
    if (comment.length===0){
        alert("내용을 입력해주세요")
        $('#new_comment').focus()
        return
    }
    let data = {"single_comment": comment,"postId": id}


    $.ajax({
        type: "POST",
        url: "/api/comments",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('댓글 등록 성공');
            window.location.reload();
        }
    });
}


function myPage(){
    $('#my_list').empty();
    $.ajax({
        type: 'GET',
        url: '/api/my_meorys',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {

                let message = response[i];
                let id = message['id'];
                let title = message['title']
                let nickname = message['nickname'];
                let modifiedAt = message['modifiedAt'];
                let view  = message['view'];
                modifiedAt = modifiedAt.substring(0,10) +" "+modifiedAt.substring(11,16)
                let tempHtml = `
                <tr id="list_${id}" onclick="open_contents(${id})">
                    <td class="lalign">${title}</td>
                    <td>${nickname}</td>
                    <td>${view}</td>
                    <td>${modifiedAt}</td>
                </tr>`;

                $('#my_list').append(tempHtml);
            }
        }
    })
}
function getList(){

    $('#my_list').empty();
    $.ajax({
        type: 'GET',
        url: '/sort/make',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {

                let message = response[i];
                let id = message['id'];
                let title = message['title']
                let nickname = message['nickname'];
                let modifiedAt = message['modifiedAt'];
                let view  = message['view'];
                modifiedAt = modifiedAt.substring(0,10) +" "+modifiedAt.substring(11,16)
                let tempHtml = `
                <tr id="list_${id}" onclick="open_contents(${id})">
                    <td class="lalign">${title}</td>
                    <td>${nickname}</td>
                    <td>${view}</td>
                    <td>${modifiedAt}</td>
                </tr>`;

                $('#my_list').append(tempHtml);
            }
        }
    })
}
function keyword_list(){
    $('#my_list').empty();
    let nickname= $('#maker').val()
    console.log(nickname)
    $.ajax({
        type: 'GET',
        url: `/sort/memorys/name/${nickname}`,
        success: function (response) {
            for (let i = 0; i < response.length; i++) {

                let message = response[i];
                let id = message['id'];
                let title = message['title']
                let nickname = message['nickname'];
                let modifiedAt = message['modifiedAt'];
                let view  = message['view'];
                modifiedAt = modifiedAt.substring(0,10) +" "+modifiedAt.substring(11,16)
                let tempHtml = `
                <tr id="list_${id}" onclick="open_contents(${id})">
                    <td class="lalign">${title}</td>
                    <td>${nickname}</td>
                    <td>${view}</td>
                    <td>${modifiedAt}</td>
                </tr>`;

                $('#my_list').append(tempHtml);
            }
        }
    })
    console.log("마지막");

}
function open_contents(id){
    //nickname은 현재 로그인해있는 아이디
    let login_user= $('#user_name').text()
    $('#choice_contents').show();
    $.ajax({
        type: 'GET',
        url: `/sort/memorys/search/${id}`,
        success: function (response) {
            let message = response;
            let nickname = message['nickname'];
            let contents = message['contents'];
            if (login_user === nickname){
                let tmp  = `<h2>${nickname}</h2>
                        <p>
                        ${contents}
                        </p>
                        <hr>
                        <br/>
                        
                        <button onclick="sut_pop()">닫기</button>
                        
                        <button onclick="edit_contents()">수정하기</button>
                        <div style="display: none" id="new_contents_form">
                            <textarea placeholder="${contents}" id="new_contents" cols="30" rows="10" ></textarea>
                            <button onclick="save_contents(${id})">저장하기</button>
                        </div>
                         
                        
                        <button onclick="delete_content(${id})">삭제하기</button>
`
                $('#now_contents').append(tmp)
            }
            else{
                let tmp  = `<h2>${nickname}</h2>
                        <p>
                        ${contents}
                        </p>
                        <hr>
                        <br/>
                        
                        <button onclick="sut_pop()">닫기</button>`
                $('#now_contents').append(tmp)
            }

        }
    })

    $.ajax({
        type: 'GET',
        url: `/sort/comments/${id}`,
        success: function (response) {
        for(let i =0; i<response.length;i++) {
            let comment_index = response[i];
            let comment_id = comment_index['id']
            let content = comment_index['single_comment']
            let writer = comment_index['writer']
            if (writer === login_user) {
            }
            if (login_user === writer) {
                let tmp2 = `<li><span>${writer}</span> : <span id="comment_${comment_id}">${content}<button onclick="delete_comment(${comment_id})">삭제</button>
                <button onclick="edit_comment('comment_${comment_id}',${comment_id},'${content}')">수정하기</button></span></li>`
                $('#comment_box').append(tmp2)
            } else {
                let tmp2 = `<li><span>${writer}</span> : <span>${content}</span></li>`
                $('#comment_box').append(tmp2)
            }
        }
        let tmp3 = `<input placeholder="댓글을 입력하세요" id="new_comment" size="90"/><button onclick="save_comment(${id})">등록하기</button>`
            $('#add_comment').append(tmp3)
        }
    })
}

function list_sort(){
    $('#my_list').empty();

    $.ajax({
        type: 'GET',
        url: '/sort/view',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {

                let message = response[i];
                let id = message['id'];
                let title = message['title']
                let nickname = message['nickname'];
                let modifiedAt = message['modifiedAt'];
                let view  = message['view'];
                modifiedAt = modifiedAt.substring(0,10) +" "+modifiedAt.substring(11,16)
                let tempHtml = `
                <tr id="list_${id}" onclick="open_contents(${id})">
                    <td class="lalign">${title}</td>
                    <td>${nickname}</td>
                    <td>${view}</td>
                    <td>${modifiedAt}</td>
                </tr>`;

                $('#my_list').append(tempHtml);
            }
        }
    })
}