/**
 * messageType    消息类型(好友或群聊)默认是消息好友类型, 初始化加载的时候根据聊天消息窗口最顶部的类型重新赋值
 * qqbqPath          表情包存放路径
 * openNewsIng    防止鼠标点击左侧切换聊天窗口的频率的锁
 * sendMessage    发送消息推送给websocket
 * openNews       消息列表左侧置顶
 * onmessage      接受来自服务端推送的消息,并显示在页面
 * refreshMsgBody 让最新的聊天消息一直处于可见
 * searchMail     模糊查询好友信息
 * createMsgBody  构建聊天内容主题
 * */
var chat = {
    messageType: "mail",
    qqbqPath: "/static/qqbq/arclist/",
    openNewsIng: false,
    createMsgBody:function(jsonData){
        //创建左边的信息
        let li=`<li class="user_item" name="`+jsonData.userId+`"  id="mail_`+jsonData.userId+`">`+
            ` <input type="hidden" id=uid_`+jsonData.userId+`>`+
            `<div class="user_head"><img src="/static/images/userhead/h3.png"/></div>`+
            `<div class="user_text">`+
                `<div class="user_name">`+jsonData.userId+`</div>`+
                `<div class="user_message"></div>`+
            `</div>`+
            `<div class="user_time"></div>`+
            `</li>`;
          $("#user_list").append(li);
        //创建右边的消息主题
        let body=`<ul  class="content" name="`+jsonData.userId+`" ></ul>`;
        $("#chatbox").append(body);
    },
    sendMessage: function () {
        if ($('#input_box font').length > 0) {
            $('#input_box font').before($('#input_box font').text())
            $('#input_box font').remove()
        }
        if ($('#input_box span').length > 0) {
            $('#input_box span').before($('#input_box span').text())
            $('#input_box span').remove()
        }
        if ($('#input_box pre').length > 0) {
            $('#input_box pre').before($('#input_box pre').text())
            $('#input_box pre').remove()
        }
        var text = $("#input_box").html(); //发送的内容
        if (text == "") {
            alert('不能发送空消息');
            return;
        }
        var liHtml = '<li class="me"><img src="' + $("#myHeadImg").val() + '"> <div contenteditable="true" class="editDiv">'+text+'</div></li>';
        $(".windowBody_active").append(liHtml);
        var chatMainId = $(".windowBody_active").attr("id"); //聊天主表id
        if(chatMainId==undefined){
            return;
        }
        this.refreshMsgBody();
        if (!window.WebSocket) {
            return;
        }
        $("#input_box").html("");
        if (websocket.readyState == WebSocket.OPEN) {
            var data = {};
            data.chatMainId = chatMainId.substring(chatMainId.indexOf("_") + 1);
            data.message = text;
            data.userId = $("#userId").val();
            if (this.messageType == "mail") {
                data.friendId = $(".windowBody_active").attr("name");
            }
            data.headImg = $("#myHeadImg").val();
            data.messageType = this.messageType;
            websocket.send(JSON.stringify(data));
            var $userlist = $("#" + this.messageType + "_" + data.chatMainId);
            $userlist.find(".user_message").html(text);
            $userlist.find(".user_time").html(getHourTime());
        } else {
            alert("和服务器连接异常！");
        }
    },
    openNews: function (obj) {
        try {
            if (this.openNewsIng) {
                console.log("return ....")
                return;
            }
            this.openNewsIng = true;
            $(".windowBody_active").removeClass("windowBody_active");
            var id = obj.attr("id").substring(obj.attr("id").indexOf("_") + 1);
            if (obj.attr("id").indexOf("mail") != -1) {
                //打开私聊的窗口
                $("#mailBody_" + id).addClass("windowBody_active");
                this.messageType = "mail";
                $("#group-common").hide();
            } else {
                //打开群聊的窗口
                $("#groupBody_" + id).addClass("windowBody_active");
                this.messageType = "group";
                $("#group-common").show();
            }
            $(".user_active").removeClass("user_active");
            $("#window-firendName").text(obj.attr("name"));
            obj.addClass("user_active");
            //把当前消息放到首发位
             $(".user_list").prepend(obj);
            chat.refreshMsgBody();
            this.openNewsIng = false;
        } catch (e) {
            console.log(e);
            //释放锁
            this.openNewsIng = false;
        }
    },
    onmessage: function (jsonData) {
        var id;
        if (jsonData.messageType == "mail") {
            id = "#uid_" + jsonData.userId;
        } else {
            id = "#" + jsonData.messageType + "_" + jsonData.chatMainId;
        }
        //如果消息窗口不存在,则构建聊天窗口
        if($(id).length==0){
            this.createMsgBody(jsonData);
        }
        var $userlist = jsonData.messageType == "mail" ? $(id).parent() : $(id);
        $userlist.find(".user_message").html(jsonData.message);
        $userlist.find(".user_time").html(jsonData.lastDay);
        //群聊消息,发送消息是本人话,不需要处理广播的消息
        if (jsonData.userId == $("#userId").val()) {
            return;
        }
        this.openNews($userlist);
        var liHtml = '<li class="other"><img src="' + jsonData.headImg + '">  <div contenteditable="true" class="editDiv">'+jsonData.message+'</div></li>';
        $(".windowBody_active").append(liHtml);
        this.refreshMsgBody();
    },
    refreshMsgBody: function () {
        var chatbox = $("#chatbox").css("height");
        //获取网页的聊天主体的可见高度
        var chatboxHeight = parseInt(chatbox.substring(0, chatbox.indexOf("p")));
        var windowBody_active = $(".windowBody_active").css("height");
        var windowBody_height = parseInt(windowBody_active.substring(0, windowBody_active.indexOf("p")));
        $(".windowBody_active").css("top", "-" + (windowBody_height - chatboxHeight) + "px");
    },
    searchMail: function (obj) {
        var condition = obj.value;
        if (obj.value == "") {
            $("#user_list").find(".user_item").show();
        }
        var usernames = $("#user_list").find(".user_name");
        usernames.each(function () {
            if (this.innerText.indexOf(condition) == -1) {
                $(this).parents(".user_item ").hide();
            } else {
                $(this).parents(".user_item ").show();
            }
        });
    }
}


function getHourTime() {
    var date = new Date();
    var hh = date.getHours().toString();
    var nn = date.getMinutes().toString();
    return hh + ":" + nn;
}

document.onkeydown = keyDownSearch;

function keyDownSearch(e) {
    // 兼容FF和IE和Opera    
    var theEvent = e || window.event;
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    // 13 代表 回车键
    if (code == 13) {
        // 要执行的函数 或者点击事件
        chat.sendMessage();
        return false;
    }
    return true;
}


$(function () {
    $(".editDiv").on("keypress",function (e) {
        e.preventDefault();
    });
    $('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});

    $(".windowSearch").focus(function () {
        $(".middle").hide();
        $(".middle:first").show();
        $(".windowSearch:first").focus();
    });
    $(".windowSearch").keyup(function () {
        chat.searchMail(this);
    });

    $('.emotion').qqFace({
        id: 'facebox',
        assign: 'input_box',
        path: chat.qqbqPath	//表情存放的路径
    });

    //左边的三图标
    var si1 = document.getElementById('si_1');
    var si2 = document.getElementById('si_2');
    var si3 = document.getElementById('si_3');
    si1.onclick = function () {
        si1.style.background = "url(/static/images/icon/head_2_1.png) no-repeat"
        si2.style.background = "";
        si3.style.background = "";
    };
    si2.onclick = function () {
        si2.style.background = "url(/static/images/icon/head_3_1.png) no-repeat"
        si1.style.background = "";
        si3.style.background = "";
    };
    si3.onclick = function () {
        si3.style.background = "url(/static/images/icon/head_4_1.png) no-repeat"
        si1.style.background = "";
        si2.style.background = "";
    };


    $(".office_text").panel({iWheelStep: 32});

    $(".sidestrip_icon a").click(function () {
        $(".sidestrip_icon a").eq($(this).index()).addClass("cur").siblings().removeClass('cur');
        $(".middle").hide().eq($(this).index()).show();
    });

    $("#input_box").hover(()=>{
        $('.windows_input').css('background', '#fff');
        $('#input_box').css('background', '#fff');
    },()=>{
        $('.windows_input').css('background', '');
        $('#input_box').css('background', '');
    });

    var obj = $("#user_list").find("li:first");
    if (obj.length!=0) {
        var liid = obj.attr("id").substring(obj.attr("id").indexOf("_") + 1);
        if (obj.attr("id").indexOf("group") == -1) {
            $("#group-common").hide();
            $("#mailBody_" + liid).addClass("windowBody_active");
        } else {
            $("#groupBody_" + liid).addClass("windowBody_active");
        }
        chat.refreshMsgBody();
    }

    $(".user_item").click(function () {
        // $(this).siblings(":hidden").show();
        var index = [].indexOf.call(this.parentNode.querySelectorAll(this.tagName), this);
        if(index==0){
            //如果已经是第一个元素,则不做置顶操作
            return;
        }
        chat.openNews($(this))
    });

    $("#send").click(function () {
        chat.sendMessage();
    });
});
























































