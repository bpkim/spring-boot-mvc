var page = 1;
var initList =[
    {
        no: "1",
        title: "제목1",
        date: "날짜1"
    }
    ,{
        no: "2",
        title: "제목2",
        date: "날짜2"
    }
    ,{
        no: "3",
        title: "제목3",
        date: "날짜3"
    }
    ,{
        no: "4",
        title: "제목4",
        date: "날짜4"
    }
    ,{
        no: "5",
        title: "제목5",
        date: "날짜5"
    }
    ,{
        no: "6",
        title: "제목6",
        date: "날짜6"
    }
    ,{
        no: "7",
        title: "제목7",
        date: "날짜7"
    }
    ,{
        no: "8",
        title: "제목8",
        date: "날짜8"
    }
    ,{
        no: "9",
        title: "제목9",
        date: "날짜9"
    }
    ,{
        no: "10",
        title: "제목10",
        date: "날짜10"
    }
];

var nowList = initList;

$(document).ready(function() {
    console.log("init");
    fn_initTable();
    fn_setEvent();
    fn_setCheckBox();
    // pagination();

    // jQuery('.pagination li:first-child').addClass("disabled");
});
function fn_initTable(){
    var tbl = $("#checkboxTestTbl").children('tbody');

    nowList.forEach(function (value, index) {
        tbl.append(    "<tr>"
            +"<td><input type=\"checkbox\" name=\"rowchk\"/></td>"
            +"<td>"+value.no+"</td>"
            +"<td>"+value.title+"</td>"
            +"<td>"+value.date+"</td>"
            +"<td><input type=\"button\" name=\"clickBtn\" value=\"클릭\"></td>"
            +"</tr>");
    });


}
function fn_setCheckBox(){
    var tbl = $("#checkboxTestTbl");

    // 테이블 헤더에 있는 checkbox 클릭시
    $(":checkbox:first", tbl).click(function(){
        // 클릭한 체크박스가 체크상태인지 체크해제상태인지 판단
        if( $(this).is(":checked") ){
            $(":checkbox", tbl).attr("checked", "checked");
        }
        else{
            $(":checkbox", tbl).removeAttr("checked");
        }

        // 모든 체크박스에 change 이벤트 발생시키기
        $(":checkbox", tbl).trigger("change");
    });

    // 헤더에 있는 체크박스외 다른 체크박스 클릭시
    $(":checkbox:not(:first)", tbl).click(function(){
        var allCnt = $(":checkbox:not(:first)", tbl).length;
        var checkedCnt = $(":checkbox:not(:first)", tbl).filter(":checked").length;

        // 전체 체크박스 갯수와 현재 체크된 체크박스 갯수를 비교해서 헤더에 있는 체크박스 체크할지 말지 판단
        if( allCnt==checkedCnt ){
            $(":checkbox:first", tbl).attr("checked", "checked");
        }
        else{
            $(":checkbox:first", tbl).removeAttr("checked");
        }
    }).change(function(){
        if( $(this).is(":checked") ){
            // 체크박스의 부모 > 부모 니까 tr 이 되고 tr 에 selected 라는 class 를 추가한다.
            // $(this).parent().parent().addClass("selected");
        }
        else{
            // $(this).parent().parent().removeClass("selected");
        }
    });

}


function fn_setEvent(){
    $("#getRow").on("click",function(){

        var rowData = new Array();
        var tdArr = new Array();

        var checkbox = $("input[name=rowchk]:checked");

        checkbox.each(function(i){
            var tr = checkbox.parent().parent().eq(i);
            var td = tr.children();

            // 체크된 row 담기
            rowData.push(tr.text());

            var no = td.eq(1).text();
            var title = td.eq(2).text();
            var date = td.eq(3).text();

            // tdArr.push(no);
            // tdArr.push(title);
            // tdArr.push(date);
            tdArr.push({
                no : no
                , title : title
                , date : date
            });


        })


        $("#resultRow").html(" 체크된 데이터 "+rowData);
        console.log(tdArr);
    });

    $("#addRow").on("click",function(){


        var rowData = new Array();
        var tdArr = new Array();

        var checkbox = $("input[name=rowchk]:checked");

        checkbox.each(function(i){
            var tr = checkbox.parent().parent().eq(i);
            var td = tr.children();

            // 체크된 row 담기
            rowData.push(tr.text());

            var no = td.eq(1).text();
            var title = td.eq(2).text();
            var date = td.eq(3).text();

            // tdArr.push(no);
            // tdArr.push(title);
            // tdArr.push(date);
            tdArr.push({
                no : no
                , title : title
                , date : date
            });


        })



        var tbl = $("#checkboxTestTbl").children('tbody');
        tdArr.forEach(function(item){
            tbl.append(    "<tr>"
                +"<td><input type=\"checkbox\" name=\"rowchk\"/></td>"
                +"<td>"+item.no+"</td>"
                +"<td>"+item.title+"</td>"
                +"<td>"+item.date+"</td>"
                +"<td><input type=\"button\" name=\"clickBtn\" value=\"클릭\"></td>"
                +"</tr>");
        });

    });


    $("#delRow").on("click",function(){


        var rowData = new Array();
        var tdArr = new Array();

        var checkbox = $("input[name=rowchk]:checked");

        checkbox.each(function(i){
            var tr = checkbox.parent().parent().eq(i);
            var td = tr.children();

            // 체크된 row 담기
            rowData.push(tr.text());

            var no = td.eq(1).text();
            var title = td.eq(2).text();
            var date = td.eq(3).text();

            // tdArr.push(no);
            // tdArr.push(title);
            // tdArr.push(date);
            tdArr.push({
                no : no
                , title : title
                , date : date
            });
            tr.remove();


        })


        $("#resultRow").html(" 삭제된 데이터 "+rowData);
        console.log(tdArr);


    });


    $("input[name=clickBtn]").on("click", function(){


        var tdArr2 = new Array();

        var str = ""
        var tdArr = new Array();	// 배열 선언
        var checkBtn = $(this);

        // checkBtn.parent() : checkBtn의 부모는 <td>이다.
        // checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
        var tr = checkBtn.parent().parent();
        var td = tr.children();

        console.log("클릭한 Row의 모든 데이터 : "+tr.text());

        var no = td.eq(1).text();
        var title = td.eq(2).text();
        var date = td.eq(3).text();

        // 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
        td.each(function(i){
            tdArr2.push(td.eq(i).text());
        });

        console.log("배열에 담긴 값 : "+tdArr2);

        tdArr.push({
            no : no
            , title : title
            , date : date
        });

        $("#resultRow").html(" 체크된 데이터 "+tr.text());
        console.log(tdArr);
    })

    $("#nextPage").on("click", function(){


        var pageNum= Number($("#pageNum").text());

        $("#pageNum").text(pageNum+1);

        var tbody = $("#checkboxTestTbl").children('tbody');
        tbody.children().remove();

        nowList.forEach(function (value, index) {
            value.no = Number(value.no) +10;
            tbody.append(    "<tr>"
                +"<td><input type=\"checkbox\" name=\"rowchk\"/></td>"
                +"<td>"+value.no+"</td>"
                +"<td>"+value.title+"</td>"
                +"<td>"+value.date+"</td>"
                +"<td><input type=\"button\" name=\"clickBtn\" value=\"클릭\"></td>"
                +"</tr>");
        });

    });


    $("#prePage").on("click", function(){

        var pageNum= Number($("#pageNum").text());
        if(pageNum == 1){
            alert("1페이지 입니다.");
            return;
        }
        $("#pageNum").text(pageNum-1);

        var tbody = $("#checkboxTestTbl").children('tbody');
        tbody.children().remove();

        nowList.forEach(function (value, index) {
            value.no = Number(value.no) - 10;
            tbody.append(    "<tr>"
                +"<td><input type=\"checkbox\" name=\"rowchk\"/></td>"
                +"<td>"+value.no+"</td>"
                +"<td>"+value.title+"</td>"
                +"<td>"+value.date+"</td>"
                +"<td><input type=\"button\" name=\"clickBtn\" value=\"클릭\"></td>"
                +"</tr>");
        });


    });

}

function pagination(){
    var req_num_row=5;
    var $tr=jQuery('tbody tr');
    var total_num_row=$tr.length;
    var num_pages=0;
    if(total_num_row % req_num_row ==0){
        num_pages=total_num_row / req_num_row;
    }
    if(total_num_row % req_num_row >=1){
        num_pages=total_num_row / req_num_row;
        num_pages++;
        num_pages=Math.floor(num_pages++);
    }

    jQuery('.pagination').append("<li><a class=\"prev\">Previous</a></li>");

    for(var i=1; i<=num_pages; i++){
        jQuery('.pagination').append("<li><a>"+i+"</a></li>");
        jQuery('.pagination li:nth-child(2)').addClass("active");
        jQuery('.pagination a').addClass("pagination-link");
    }

    jQuery('.pagination').append("<li><a class=\"next\">Next</a></li>");

    $tr.each(function(i){
        jQuery(this).hide();
        if(i+1 <= req_num_row){
            $tr.eq(i).show();
        }
    });

    jQuery('.pagination a').click('.pagination-link', function(e){
        e.preventDefault();
        $tr.hide();
        var page=jQuery(this).text();
        var temp=page-1;
        var start=temp*req_num_row;
        var current_link = temp;

        jQuery('.pagination li').removeClass("active");
        jQuery(this).parent().addClass("active");

        for(var i=0; i< req_num_row; i++){
            $tr.eq(start+i).show();
        }

        if(temp >= 1){
            jQuery('.pagination li:first-child').removeClass("disabled");
        }
        else {
            jQuery('.pagination li:first-child').addClass("disabled");
        }

    });

    jQuery('.prev').click(function(e){
        e.preventDefault();
        jQuery('.pagination li:first-child').removeClass("active");
    });

    jQuery('.next').click(function(e){
        e.preventDefault();
        jQuery('.pagination li:last-child').removeClass("active");
    });

}
