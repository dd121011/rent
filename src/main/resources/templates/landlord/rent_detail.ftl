<div id="rentDetailDiv" style="padding: 20px 40px; display: none" >
    <form class="layui-form" lay-filter="rentDetailFormFilter" action="">
        <table class="layui-table" lay-even lay-skin="line">
            <tbody id="rentDetailTableTbody"></tbody>
        </table>
        <hr class="layui-bg-green">
        <table class="layui-table" id="rentDetailItermTable" lay-filter="rentItermTableFilter"></table>
    </form>
</div>

<script type="text/html" id="rentDetailTemplete">
    <tr>
        <th>房间：</th>
        <td><label>{{ d.room.building.name }}-{{ d.room.roomNo }}</td>
        <th>租客：</th>
        <td colspan="3">{{ d.bargin.name }}</td>
    </tr>
    <tr>
        <th>房租月份：</th>
        <td>{{ d.rentMonth }}</td>
        <th>收据单号：</th>
        <td colspan="3">{{ d.rentNo }}</td>
    </tr>
    <tr>
        <th>总费用：</th>
        <td>{{ d.fee/100 }}元</td>
        <th>折扣费用：</th>
        <td>{{ d.count/100 }}元</td>
        <th>实际费用：</th>
        <td>{{ d.realFee/100 }}元</td>
    </tr>
</script>