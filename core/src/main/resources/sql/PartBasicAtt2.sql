select wm.ida2a2, --部件唯一标识
       wm.NAME, --名称
       wm.WTPARTNUMBER                 "number", --编号
       wm.CREATESTAMPA2                createtime, --创建时间
       wm.MARKFORDELETEA2, --是否删除
       wm.MODIFYSTAMPA2                modtime, --修改时间
       wp.STATESTATE,
       wm.CLASSNAMEKEYCONTAINERREFEREN, --所属容器类型
       wm.IDA3CONTAINERREFERENCE, --"所属容器。外键，根据所属容器类型值（PDMLinkProduct/WTLibrary）对应关联表"
       wl.NAMECONTAINERINFO
  from plm.WTPartMaster wm, WTPart wp, plm.WTLibrary wl
 where wm.ida2a2 = wp.ida2a2
   and wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.inf.library.WTLibrary'
   and wm.ida2a2 = wl.ida2a2(+)
union all
select wm.ida2a2, --部件唯一标识
       wm.NAME, --名称
       wm.WTPARTNUMBER                 "number", --编号
       wm.CREATESTAMPA2                createtime, --创建时间
       wm.MARKFORDELETEA2, --是否删除
       wm.MODIFYSTAMPA2                modtime, --修改时间
       wm.CLASSNAMEKEYCONTAINERREFEREN, --所属容器类型
       wm.IDA3CONTAINERREFERENCE, --"所属容器。外键，根据所属容器类型值（PDMLinkProduct/WTLibrary）对应关联表"
       pp.NAMECONTAINERINFO
  from plm.WTPartMaster wm, plm.PDMLinkProduct pp--产品容器信息表
 where wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.pdmlink.PDMLinkProduct'
   and wm.ida2a2 = pp.ida2a2(+);
   
   --一个主表对应多个从表
   select cwp.*
     from plm.WTPartMaster cwm, --子部件信息主表
          plm.WTPart       cwp --子部件信息从表
    where cwm.ida2a2 = cwp.IDA3MASTERREFERENCE
      and cwm.ida2a2 = 2847975;
