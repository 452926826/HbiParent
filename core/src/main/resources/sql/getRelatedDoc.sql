--获取产品关联文
select wm.NAME, --名称
       wm.WTPARTNUMBER "number", --编号
       wtm.IDA2A2, --文档唯一标识
       wtm.WTDOCUMENTNUMBER docnum, --文档编号
       wtm.NAME docname, --文档名称
       wu.name modifyer, --修改人
       wtm.MODIFYSTAMPA2 modtime, --修改时间
       decode(wt.STATECHECKOUTINFO, 'c/i', '检入', 'c/o', '检出') status, --状况：c/i检入；c/o检出
       wt.STATESTATE state, --状态
       wl.NAMECONTAINERINFO container, --容器名称
       wts.DISPLAYNAMEKEY doctype, --文档类型 类型显示名称
       wts.INTHID, --类型内码 
       wsl.IDA3A5, --部件信息从表ID
       wsl.IDA2A2 linkida2a2 --文档与部件关系唯一标识
  from plm.WTDocumentMaster       wtm, --文档信息主表
       plm.WTDocument             wt, --文档信息从表
       plm.WTLibrary              wl, --存储库信息表
       plm.WTPARTDESCRIBELINK     wsl, --部件与文档关联信息表  IDA3B5 --外键，与文档信息从表关联    IDA3A5外键，与部件信息从表关联
       plm.WTTYPEDEFINITIONMASTER wts, --类型定义主表
       plm.WTTYPEDEFINITION       wtd, --类型定义从表
       plm.WTUser                 wu, --用户信息表
       plm.WTPartMaster           wm, --部件信息主表
       plm.WTPart                 wp --部件信息从表
 where wtm.IDA2A2 = wt.IDA3MASTERREFERENCE
   and wtm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.inf.library.WTLibrary'
   and wtm.IDA3CONTAINERREFERENCE = wl.ida2a2
   and wt.IDA2A2 = wsl.IDA3B5 --与文档信息从表关联 
   and wt.IDA2TYPEDEFINITIONREFERENCE = wtd.IDA2A2 --外键，与类型定义从表关联
   and wts.IDA2A2 = wtd.IDA3MASTERREFERENCE
   and wt.IDA3A2OWNERSHIP = wu.IDA2A2
   and wm.ida2a2 = wp.IDA3MASTERREFERENCE
   and wsl.IDA3A5 = wp.ida2a2 --1182744070 --与部件信息从表关联
   and wm.ida2a2 = 2004939
union all
select wm.NAME, --名称
       wm.WTPARTNUMBER "number", --编号
       wtm.IDA2A2, --文档唯一标识
       wtm.WTDOCUMENTNUMBER docnum, --文档编号
       wtm.NAME docname, --文档名称
       wu.name modifyer, --修改人
       wtm.MODIFYSTAMPA2 modtime, --修改时间
       decode(wt.STATECHECKOUTINFO, 'c/i', '检入', 'c/o', '检出') status, --状况：c/i检入；c/o检出
       wt.STATESTATE state, --状态
       pp.NAMECONTAINERINFO container, --容器名称
       wts.DISPLAYNAMEKEY doctype, --文档类型 类型显示名称
       wts.INTHID, --类型内码 
       wsl.IDA3A5, --部件信息从表ID
       wsl.IDA2A2 linkida2a2 --文档与部件关系唯一标识
  from plm.WTDocumentMaster       wtm, --文档信息主表
       plm.WTDocument             wt, --文档信息从表
       plm.PDMLinkProduct         pp, --产品容器信息表
       plm.WTPARTDESCRIBELINK     wsl, --部件与文档关联信息表  IDA3B5 --外键，与文档信息从表关联    IDA3A5外键，与部件信息从表关联
       plm.WTTYPEDEFINITIONMASTER wts, --类型定义主表
       plm.WTTYPEDEFINITION       wtd, --类型定义从表
       plm.WTUser                 wu, --用户信息表
       plm.WTPartMaster           wm, --部件信息主表
       plm.WTPart                 wp --部件信息从表
 where wtm.IDA2A2 = wt.IDA3MASTERREFERENCE
   and wtm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.pdmlink.PDMLinkProduct'
   and wm.IDA3CONTAINERREFERENCE = pp.ida2a2
   and wt.IDA2A2 = wsl.IDA3B5 --与文档信息从表关联 
   and wt.IDA2TYPEDEFINITIONREFERENCE = wtd.IDA2A2 --外键，与类型定义从表关联
   and wts.IDA2A2 = wtd.IDA3MASTERREFERENCE
   and wt.IDA3A2OWNERSHIP = wu.IDA2A2
   and wm.ida2a2 = wp.IDA3MASTERREFERENCE
   and wsl.IDA3A5 = wp.ida2a2 --1182744070 --与部件信息从表关联
   --and wm.ida2a2 = 2004939
   and wm.WTPARTNUMBER = 'TE020-351001'
