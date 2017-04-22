--获取产品替换物料
CREATE OR REPLACE VIEW POC_REPLACE_PART_V AS
select wps.IDA3A5           IDA2A2, --BOM关系唯一标识
       wm.NAME, --替换物料名称
       wm.WTPARTNUMBER      "number", --替换物料编号
       wl.NAMECONTAINERINFO container --容器名称
  from plm.WTPARTSUBSTITUTELINK wps, --产品替换关系信息表  IDA3A5外键，与Bom关联信息表关联
       plm.WTPartMaster         wm, --部件信息主表
       plm.WTLibrary            wl --存储库信息表
--,plm.WTPartusageLink      wpl --Bom关联信息表
 where wps.IDA3B5 = wm.ida2a2
   and wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.inf.library.WTLibrary'
   and wm.IDA3CONTAINERREFERENCE = wl.ida2a2
   and wps.IDA3A5 = 106822500 -- wpl.IDA2A2
union all
select wps.IDA3A5           IDA2A2, --BOM关系唯一标识
       wm.NAME, --替换物料名称
       wm.WTPARTNUMBER      "number", --替换物料编号
       pp.NAMECONTAINERINFO container --容器名称
  from plm.WTPARTSUBSTITUTELINK wps, --产品替换关系信息表  IDA3A5外键，与Bom关联信息表关联
       plm.WTPartMaster         wm, --部件信息主表
       plm.PDMLinkProduct       pp --产品容器信息表
--,plm.WTPartusageLink      wpl --Bom关联信息表
 where wps.IDA3B5 = wm.ida2a2
   and wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.pdmlink.PDMLinkProduct'
   and wm.IDA3CONTAINERREFERENCE = pp.ida2a2
   and wps.IDA3A5 = 2179659580 -- wpl.IDA2A2
