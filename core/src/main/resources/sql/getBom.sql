--获得BOM关系及属性
select wpl.IDA2A2              linkida2a2, --BOM关系唯一标识 
       null                    childmasterida2a2, --子件Master唯一标
       wpl.IDA3B5              "id", --子件唯一标识 "外键，与部件信息主表关联。Bom子项节点"
       pwp.IDA3MASTERREFERENCE parentid, --父件唯一标识
       --wpl.IDA3A5, --"外键，与部件信息从表关联。Bom父项节点"
       null state, --是否有子节点（有：closed,没有：open）
       null total, --子节点数量
       wpl.VALUEB7 linenum, --行号
       null occurrence, --位号
       wpl.AMOUNTA7 amount, --数量
       wpl.UNITA7 unit, --  单位
       cwm.WTPARTNUMBER childnum, --子件编号
       cwm.NAME childname, --子件名称
       cwp.STATESTATE partstate, --状态
       decode(cwp.STATECHECKOUTINFO, 'c/i', '检入', 'c/o', '检出') status, --状况：c/i检入；c/o检出
       null "version", --版本
       null linkweight, --BOM关系重量
       null "source", --关系来源
       null isvirtual, --是否虚拟键
       null priory, --优先级
       null isBoard, --是否光板
       null replacement, --替换组
       null isborrow, --是否借用件
       null iskey, --是否关键件
       null discription, --备注
       null isselected, --是否选配件,
       cwv.name "view", --视图
       null material, --材料编码
       null producttype, --产品类型
       null classification, --产品分类
       null sapversion, --SAP版本
       null drawingsize, --图幅
       null cadtype, --图纸类型
       null drawingtype, --图样类型
       null "specification", --型号规格
       null marlevel, --物料等级
       null weight --物料单重

  from plm.WTPartusageLink wpl, --Bom关联信息表
       plm.WTPart          pwp, --父部件信息从表
       plm.WTPartMaster    cwm, --子部件信息主表
       plm.WTPart          cwp, --子部件信息从表
       plm.WTView          cwv --子视图信息表
 where wpl.IDA3A5 = pwp.IDA2A2
   and wpl.IDA3B5 = cwm.IDA2A2
   and cwm.ida2a2 = cwp.IDA3MASTERREFERENCE
   and cwp.IDA3VIEW = cwv.IDA2A2
      
   and wpl.IDA2A2 = 7626371;
