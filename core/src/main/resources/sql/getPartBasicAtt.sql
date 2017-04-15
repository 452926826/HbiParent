select wm.ida2a2, --部件唯一标识
       wm.NAME, --名称
       wm.WTPARTNUMBER "number", --编号
       wm.MARKFORDELETEA2, --是否删除
       decode(wp.STATECHECKOUTINFO, 'c/i', '检入', 'c/o', '检出') status, --状况：c/i检入；c/o检出
       wv.name "view", --视图
       wp.STATESTATE, --状态
       wm.MODIFYSTAMPA2 modtime, --修改时间
       wm.CREATESTAMPA2 createtime, --创建时间
       wl.NAMECONTAINERINFO container, --容器名称
       wu.name modifyer, --修改人
       wp.ITERATIONIDA2ITERATIONINFO, --小版本信息
       wp.LATESTITERATIONINFO, --大版本最新标识：1大版本最新
       wp.VERSIONIDA2VERSIONINFO --大版本信息
  from plm.WTPartMaster wm, --部件信息主表
       plm.WTPart       wp, --部件信息从表
       plm.WTView       wv, --视图信息表
       plm.WTLibrary    wl, --存储库信息表
       plm.WTUser       wu --用户信息表
 where wm.ida2a2 = wp.IDA3MASTERREFERENCE
   and wp.IDA3VIEW = wv.IDA2A2
   and wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.inf.library.WTLibrary'
   and wm.IDA3CONTAINERREFERENCE = wl.ida2a2
   and wp.IDA3A2OWNERSHIP = wu.IDA2A2
union all
select wm.ida2a2, --部件唯一标识
       wm.NAME, --名称
       wm.WTPARTNUMBER "number", --编号
       wm.MARKFORDELETEA2, --是否删除
       decode(wp.STATECHECKOUTINFO, 'c/i', '检入', 'c/o', '检出') status, --状况：c/i检入；c/o检出
       wv.name "view", --视图
       wp.STATESTATE, --状态
       wm.MODIFYSTAMPA2 modtime, --修改时间
       wm.CREATESTAMPA2 createtime, --创建时间
       pp.NAMECONTAINERINFO container, --容器名称
       wu.name modifyer, --修改人
       wp.ITERATIONIDA2ITERATIONINFO, --小版本信息
       wp.LATESTITERATIONINFO, --大版本最新标识：1大版本最新
       wp.VERSIONIDA2VERSIONINFO --大版本信息
  from plm.WTPartMaster   wm, --部件信息主表
       plm.WTPart         wp, --部件信息从表
       plm.WTView         wv, --视图信息表
       plm.PDMLinkProduct pp, --产品容器信息表
       plm.WTUser         wu --用户信息表
 where wm.ida2a2 = wp.IDA3MASTERREFERENCE
   and wp.IDA3VIEW = wv.IDA2A2
   and wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.pdmlink.PDMLinkProduct'
   and wm.IDA3CONTAINERREFERENCE = pp.ida2a2
   and wp.IDA3A2OWNERSHIP = wu.IDA2A2
