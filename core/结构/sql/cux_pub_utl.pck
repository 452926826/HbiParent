create or replace package cux_pub_utl is

  /*获取string value*/
  function get_string_value(p_displayname    in varchar2,
                            p_classnamekeya4 in varchar2,
                            p_IDA3A4         in number) return varchar2;

  /*获取number value*/
  function get_float_value(p_displayname    in varchar2,
                           p_classnamekeya4 in varchar2,
                           p_IDA3A4         in number) return number;

  /*获取位号*/
  function get_occurrence(p_classnamekeylinkreference in varchar2,
                          p_ida3linkreference         in number)
    return varchar2;

end cux_pub_utl;
/
create or replace package body cux_pub_utl is

  /*获取string value*/
  function get_string_value(p_displayname    in varchar2,
                            p_classnamekeya4 in varchar2,
                            p_IDA3A4         in number) return varchar2 is
  
    cursor cur_data is
      select sv.VALUE2 --属性值
        from plm.Stringvalue      sv, --字符属性值信息表
             plm.Stringdefinition sf --字符属性定义信息表
       where sv.IDA3A6 = sf.IDA2A2
         and sf.displayname = p_displayname
         and sv.classnamekeya4 = p_classnamekeya4
         and sv.IDA3A4 = p_IDA3A4;
    l_value varchar2(240);
  begin
    open cur_data;
    fetch cur_data
      into l_value;
    close cur_data;
    return l_value;
  end get_string_value;

  /*获取number value*/
  function get_float_value(p_displayname    in varchar2,
                           p_classnamekeya4 in varchar2,
                           p_IDA3A4         in number) return number is
  
    cursor cur_data is
      select fv.VALUE --属性值
        from plm.FLOATvalue      fv, --浮点型属性值信息表
             plm.FLOATdefinition ff --浮点型属性定义表
       where fv.IDA3A6 = ff.IDA2A2
         and ff.displayname = p_displayname
         and fv.CLASSNAMEKEYA4 = p_classnamekeya4
         and fv.IDA3A4 = p_IDA3A4;
    l_value number;
  begin
    open cur_data;
    fetch cur_data
      into l_value;
    close cur_data;
    return l_value;
  end get_float_value;

  /*获取位号*/
  function get_occurrence(p_classnamekeylinkreference in varchar2,
                          p_ida3linkreference         in number)
    return varchar2 is
  
    cursor cur_data is
      select puo.name
        from plm.partusesoccurrence puo
       where puo.classnamekeylinkreference = p_classnamekeylinkreference
         and puo.ida3linkreference = p_ida3linkreference
       order by puo.name;
  
    l_occurrence varchar2(240);
  begin
    for rec in cur_data loop
      if l_occurrence is null then
        l_occurrence := rec.name;
      else
        l_occurrence := l_occurrence || ',' || rec.name;
      end if;
    end loop;
    return l_occurrence;
  end get_occurrence;

--获得BOM关系及属性
/*
select level, t.*
  from cux_bom_v t
 start with t.parentid = 2227773156
connect by prior t.id = t.parentid*/

end cux_pub_utl;
/
