<div id="roomBarginDiv" style="padding: 10px 40px; display: none" >
    <form class="layui-form" lay-filter="roomBarginFormFilter" action="">
        <p>合同内容：</p>
        <table class="layui-table" style="margin: 0" lay-even lay-skin="line">
            <tbody id="roomBarginTableTbody"></tbody>
        </table>
        <hr class="layui-bg-green">
        <p>额外收费项：</p>
        <table class="layui-table" style="margin: 0" id="roomBarginItermTable" lay-filter="roomBarginItermTableFilter"></table>
    </form>
</div>

<script type="text/html" id="roomBarginTemplete">
    <tr>
        <th>甲方（出租方）：</th>
        <td><label>{{ d.landlordName }}</td>
        <th>乙方（承租方）：</th>
        <td colspan="3">{{ d.bargin.name }}</td>
    </tr>
    <tr>
        <th>手机号码：</th>
        <td>{{ d.bargin.phone }}</td>
        <th>身份证号：</th>
        <td colspan="3">{{ d.bargin.idCard }}</td>
    </tr>
    <tr>
        <th>租赁房间：</th>
        <td colspan="1">{{ d.building.name }}-{{ d.roomNo }}</td>
        <th>租赁地址：</th>
        <td colspan="3">{{ d.building.address }}</td>
    </tr>
    <tr>
        <th>租金：</th>
        <td>{{ d.bargin.rentFee/100 }}</td>
        <th>收租日：</th>
        <td>{{ d.bargin.rentDay }}日</td>
    </tr>
    <tr>
        <th>押付方式：</th>
        <td>押{{ d.bargin.guaranty }}付{{ d.bargin.pay }}</td>
        <th>签订时间：</th>
        <td colspan="3">{{ d.signTs }}</td>
    </tr>
    <tr>
        <th>入住时间：</th>
        <td>{{ d.liveTs }}</td>
        <th>退租时间：</th>
        <td colspan="3">{{ d.leaveTs }}</td>
    </tr>
    <tr>
        <th>配套设施：</th>
        <td colspan="5">{{ d.facilitiesName }}</td>
    </tr>
</script>