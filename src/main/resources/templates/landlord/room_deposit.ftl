<div id="roomDepositDiv" style="padding: 10px 40px; display: none" >
    <form class="layui-form" lay-filter="roomDepositFormFilter" action="">
        <p>押金内容：</p>
        <table class="layui-table" style="margin: 0" lay-even lay-skin="line">
            <tbody id="roomDepositTableTbody"></tbody>
        </table>
        <hr class="layui-bg-green">
        <p>押金明细：</p>
        <table class="layui-table" style="margin: 0" id="roomDepositItermTable" lay-filter="roomDepositItermTableFilter"></table>
    </form>
</div>

<script type="text/html" id="roomDepositTemplete">
    <tr>
        <th>房间：</th>
        <td><label>{{ d.building.name }}-{{ d.roomNo }}</td>
        <th>租客：</th>
        <td colspan="3">{{ d.bargin.name }}</td>
    </tr>
    <tr>
        <th>押金单号：</th>
        <td>{{ d.deposit.depositNo }}</td>
        <th>签订时间：</th>
        <td colspan="3">{{ d.signTs }}</td>
    </tr>
    <tr>
        <th>总费用：</th>
        <td>{{ d.deposit.fee/100 }}元</td>
        <th>支付状态：</th>
        <td>
            {{#  if(d.deposit.payTs > 0){ }}
            已支付
            {{#  } else { }}
            未支付
            {{#  } }}
        </td>
    </tr>
    <tr>
        <th>支付时间：</th>
        <td>{{ d.payTs }}</td>
        <th>支付渠道：</th>
        <td>
            {{#  if(d.deposit.payTs > 0){ }}
            {{ d.deposit.channel }}
            {{#  } }}
        </td>
    </tr>
</script>