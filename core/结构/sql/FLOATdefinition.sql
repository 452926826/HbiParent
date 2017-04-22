select ff.DISPLAYNAME, --属性显示名
       ff.NAME, -- 属性内码
       ff.IDA2A2
  from plm.FLOATdefinition ff --浮点型属性定义表
 where ff.displayname = '物料等级';

select fv.CLASSNAMEKEYA4, -- 对象类型
       fv.IDA3A4, --  外键，与对象表关联
       fv.IDA3A6, --   外键，与浮点型属性定义表关联
       fv.VALUE, --属性值
       fv.IDA2A2
  from plm.FLOATvalue fv --浮点型属性值信息表
;

select ff.DISPLAYNAME, --属性显示名
       ff.NAME, -- 属性内码
       ff.IDA2A2,
       fv.CLASSNAMEKEYA4, -- 对象类型
       fv.IDA3A4, --  外键，与对象表关联
       fv.IDA3A6, --   外键，与浮点型属性定义表关联
       fv.VALUE, --属性值
       fv.IDA2A2,
       fv.*
  from plm.FLOATvalue      fv, --浮点型属性值信息表
       plm.FLOATdefinition ff --浮点型属性定义表
 where fv.IDA3A6 = ff.IDA2A2
   and ff.displayname = '物料单重'
   and fv.CLASSNAMEKEYA5 is not null;
