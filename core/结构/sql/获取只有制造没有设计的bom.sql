select wpl.IDA2A2 linkida2a2, --BOM关系唯一标识
       cwm.ida2a2 childmasterida2a2, --子件Master唯一标
       wpl.IDA3A5 parentid, --
       cwp.ida2a2 id,
       cwm.WTPARTNUMBER childnum, --子件编号
       cwm.NAME childname, --子件名称
       (select count(1)
          from plm.WTPartusageLink pwpl
         where pwpl.IDA3A5 = cwp.ida2a2) total, --子节点数量
       wpl.VALUEB7 linenum, --行号
       cux_pub_utl.get_occurrence(p_classnamekeylinkreference => 'wt.part.WTPartUsageLink',
                                  p_ida3linkreference         => wpl.IDA2A2) occurrence, --位号
       wpl.AMOUNTA7 amount, --数量
       wpl.UNITA7 unit, --  单位
       cwp.STATESTATE partstate, --状态
       decode(cwp.STATECHECKOUTINFO, 'c/i', '检入', 'c/o', '检出') status, --状况：c/i检入；c/o检出
       cwp.VERSIONIDA2VERSIONINFO || '.' || cwp.ITERATIONIDA2ITERATIONINFO version, --版本 大版本信息.小版本信息
       cwv.name "view", --视图
       cwp.IDA3VIEW

  from plm.WTPartusageLink wpl, --Bom关联信息表
       plm.WTPart          pwp, --父部件信息从表
       plm.WTPartMaster    cwm, --子部件信息主表
       plm.WTPart          cwp, --子部件信息从表
       plm.WTView          cwv --子视图信息表
 where wpl.IDA3A5 = pwp.IDA2A2
   and wpl.IDA3B5 = cwm.IDA2A2
   and cwm.ida2a2 = cwp.IDA3MASTERREFERENCE
   and cwp.IDA3VIEW = cwv.IDA2A2
   and pwp.IDA3VIEW = 3252
      --and wpl.IDA3A5 = 7540438
      --and cwm.ida2a2 = 47038093
   and not exists (select null
          from plm.WTPart cwpn
         where cwm.ida2a2 = cwpn.IDA3MASTERREFERENCE
           and cwpn.IDA3VIEW = 3252 --设计视图
        )
   and rownum = 1;
