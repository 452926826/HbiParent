select sf.DISPLAYNAME, --属性显示名
       sf.NAME, -- 属性内码
       sf.IDA2A2
  from plm.Stringdefinition sf --字符属性定义信息表
 where sf.displayname = '物料等级';

select sv.CLASSNAMEKEYA4, -- 对象类型
       sv.IDA3A4, --  外键，与对象表关联
       sv.IDA3A6, --   外键，与字符属性定义表关联
       sv.VALUE2, --属性值
       sv.IDA2A2
  from plm.Stringvalue sv --字符属性值信息表
;

select sf.DISPLAYNAME, --属性显示名
       sf.NAME, -- 属性内码
       sf.IDA2A2,
       sv.CLASSNAMEKEYA4, -- 对象类型
       sv.IDA3A4, --  外键，与对象表关联
       sv.IDA3A6, --   外键，与字符属性定义表关联
       sv.VALUE2, --属性值
       sv.IDA2A2,
       sv.*
  from plm.Stringvalue      sv, --字符属性值信息表
       plm.Stringdefinition sf --字符属性定义信息表
 where sv.IDA3A6 = sf.IDA2A2
   and sf.displayname = '型号规格';
