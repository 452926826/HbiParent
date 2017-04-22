select cwm.ida2a2 childmasterida2a2, --子件Master唯一标
       cwp.ida2a2 id,
       cwm.WTPARTNUMBER childnum, --子件编号
       cwm.NAME childname, --子件名称
       cwp.STATESTATE partstate, --状态
       decode(cwp.STATECHECKOUTINFO, 'c/i', '检入', 'c/o', '检出') status, --状况：c/i检入；c/o检出
       cwp.VERSIONIDA2VERSIONINFO || '.' || cwp.ITERATIONIDA2ITERATIONINFO version, --版本 大版本信息.小版本信息
       cwv.name "view", --视图
       cwp.IDA3VIEW

  from plm.WTPartMaster cwm, --子部件信息主表
       plm.WTPart       cwp, --子部件信息从表
       plm.WTView       cwv --子视图信息表
 where cwm.ida2a2 = cwp.IDA3MASTERREFERENCE
   and cwp.IDA3VIEW = cwv.IDA2A2
 -- and cwp.ida2a2 = 210638092
 --TE9999000000
   and cwm.WTPARTNUMBER ='6102H04-0000000'
   --and cwp.VERSIONIDA2VERSIONINFO || '.' || cwp.ITERATIONIDA2ITERATIONINFO ='A.B.1'
   ;
