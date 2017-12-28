$(document).ready(function () {
    table = $("#itemsTable").DataTable({
        paginate: true,
        lengthChange: false,
        filter: true,
        sort: true,
        info: true,
        autoWidth: true,
        processing: true,
        serverSide: true,
        ajax: '/girls',
        columns: [
            {"data": "id"},
            {"data": "name"},
            {"data": "age"}
        ],
        lengthMenu: [[15, 30, 45], ["15 条", "30 条", "45 条"]],
        language: {
            searchPlaceholder: "搜索...",
            processing: "数据加载中……",
            lengthMenu: "每页显示 _MENU_ 条",
            info: "从 _START_ 到 _END_ 条，总共 _TOTAL_ 条记录",
            zeroRecords: "没有记录",
            infoEmpty: "暂无记录"
        }
    });
});