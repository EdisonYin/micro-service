/**
 * Created by webmxj on 2017/12/18.
 */
//对应的是表头
var columnDefs=[
    {headerName:'姓名',field:'name'},
    {headerName:'性别',field:'sex'},
    {headerName:'年龄',field:'age'},
];

//与表头对应的表格的数据; key来至于上方定义的field;
var rowData=[
    {name:'张三',sex:'男',age:'100'},
    {name:'李四',sex:'女',age:'5'},
    {name:'王五',sex:'未知',age:'50'},
]

//将上面的数据放入一个数组中；
var gridOptions = {
    columnDefs: columnDefs,
    rowData: rowData,
    onGridReady: function () {//表格创建完成后执行
        gridOptions.api.sizeColumnsToFit();//调整表格大小自适应
    }
};
//在dom加载完成后 获取我们刚才定义的id 然后将它们一起传入对象中就大功告成了；
document.addEventListener("DOMContentLoaded", function () {
    var eGridDiv = document.querySelector('#myGrid');
    new agGrid.Grid(eGridDiv, gridOptions);
});