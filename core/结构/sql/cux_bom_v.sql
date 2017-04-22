create or replace view cux_bom_v as
select wpl.IDA2A2 linkida2a2, --BOM关系唯一标识
       cwm.ida2a2 childmasterida2a2, --子件Master唯一标
       wpl.IDA3A5 parentid, --
       cwp.ida2a2 id,
       cwm.WTPARTNUMBER childnum, --子件编号
       cwm.NAME childname, --子件名称
       case (select count(1)
           from plm.WTPartusageLink pwpl
          where pwpl.IDA3A5 = cwp.ida2a2)
         when 0 then
          'open'
         else
          'closed'
       end state, --是否有子节点（有：closed,没有：open）
       case (select count(1)
           from plm.WTPartusageLink pwpl
          where pwpl.IDA3A5 = cwp.ida2a2)
         when 0 then
          'false'
         else
          'true'
       end hasChildren, --是否有子节点（有：closed,没有：open）
       case (select count(1)
           from plm.WTPartusageLink pwpl
          where pwpl.IDA3A5 = cwp.ida2a2)
         when 0 then
          'false'
         else
          'true'
       end has_children, --是否有子节点（有：closed,没有：open）
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
       cux_pub_utl.get_string_value(p_displayname    => 'BOM关系重量',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) linkweight, --BOM关系重量
       cux_pub_utl.get_string_value(p_displayname    => '关系来源',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) source, --关系来源
       cux_pub_utl.get_string_value(p_displayname    => '是否虚拟键',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) isvirtual, --是否虚拟键
       cux_pub_utl.get_string_value(p_displayname    => '优先级',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) priory, --优先级
       cux_pub_utl.get_string_value(p_displayname    => '是否光板',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) isBoard, --是否光板
       cux_pub_utl.get_string_value(p_displayname    => '替换组',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) replacement, --替换组
       cux_pub_utl.get_string_value(p_displayname    => '是否借用件',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) isborrow, --是否借用件
       cux_pub_utl.get_string_value(p_displayname    => '是否关键件',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) iskey, --是否关键件
       cux_pub_utl.get_string_value(p_displayname    => '备注',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) discription, --备注
       cux_pub_utl.get_string_value(p_displayname    => '是否选配件',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) isselected, --是否选配件,
       cwv.name "view", --视图
       cux_pub_utl.get_string_value(p_displayname    => '图幅',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) material, --材料编码
       cux_pub_utl.get_string_value(p_displayname    => '图幅',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) producttype, --产品类型
       cux_pub_utl.get_string_value(p_displayname    => '图幅',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) classification, --产品分类
       cwp.VERSIONIDA2VERSIONINFO sapversion, --SAP版本
       cux_pub_utl.get_string_value(p_displayname    => '图幅',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) drawingsize, --图幅
       cux_pub_utl.get_string_value(p_displayname    => '图纸类型',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) cadtype, --图纸类型
       cux_pub_utl.get_string_value(p_displayname    => '图样类型',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) drawingtype, --图样类型
       cux_pub_utl.get_string_value(p_displayname    => '型号规格',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) specification, --型号规格
       cux_pub_utl.get_string_value(p_displayname    => '物料等级',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) marlevel, --物料等级
       cux_pub_utl.get_float_value(p_displayname    => '物料单重',
                                   p_classnamekeya4 => 'wt.part.WTPart',
                                   p_ida3a4         => cwp.ida2a2) weight --物料单重

  from plm.WTPartusageLink wpl, --Bom关联信息表
       plm.WTPart          pwp, --父部件信息从表
       plm.WTPartMaster    cwm, --子部件信息主表
       plm.WTPart          cwp, --子部件信息从表
       plm.WTView          cwv --子视图信息表
 where wpl.IDA3A5 = pwp.IDA2A2
   and wpl.IDA3B5 = cwm.IDA2A2
   and cwm.ida2a2 = cwp.IDA3MASTERREFERENCE
   and cwp.IDA3VIEW = cwv.IDA2A2
   and cwp.ITERATIONIDA2ITERATIONINFO =
       (select max(to_number(cwpm.ITERATIONIDA2ITERATIONINFO))
          from plm.WTPart cwpm
         where cwm.ida2a2 = cwpm.IDA3MASTERREFERENCE
           and cwp.VERSIONIDA2VERSIONINFO = cwpm.VERSIONIDA2VERSIONINFO)
   and cwp.VERSIONIDA2VERSIONINFO =
       (select max(cwpm.VERSIONIDA2VERSIONINFO)
          from plm.WTPart cwpm
         where cwm.ida2a2 = cwpm.IDA3MASTERREFERENCE);
