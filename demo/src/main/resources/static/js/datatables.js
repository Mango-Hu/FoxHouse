$(document).ready( function () {
    $('#table_id_example').DataTable({
        //"pagingType" : "full_numbers"

        /*"scrollY": 200,              //垂直滚动条
        "scrollCollapse": true,
        "jQueryUI": true*/

        //stateSave: true  //保存上次页面

        /*columnDefs: [ {
            targets: [ 0 ],
            orderData: [ 0, 1 ]  //如果第一列进行排序，有相同数据则按照第二列顺序排列
        }, {
            targets: [ 1 ],
            orderData: [ 1, 0 ]  //如果第二列进行排序，有相同数据则按照第一列顺序排列
        } ]*/

    });
} );