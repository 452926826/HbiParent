create or replace package cux_pub_utl is

  /*��ȡstring value*/
  function get_string_value(p_displayname    in varchar2,
                            p_classnamekeya4 in varchar2,
                            p_IDA3A4         in number) return varchar2;

  /*��ȡnumber value*/
  function get_float_value(p_displayname    in varchar2,
                           p_classnamekeya4 in varchar2,
                           p_IDA3A4         in number) return number;

  /*��ȡλ��*/
  function get_occurrence(p_classnamekeylinkreference in varchar2,
                          p_ida3linkreference         in number)
    return varchar2;

end cux_pub_utl;
/
create or replace package body cux_pub_utl is

  /*��ȡstring value*/
  function get_string_value(p_displayname    in varchar2,
                            p_classnamekeya4 in varchar2,
                            p_IDA3A4         in number) return varchar2 is
  
    cursor cur_data is
      select sv.VALUE2 --����ֵ
        from plm.Stringvalue      sv, --�ַ�����ֵ��Ϣ��
             plm.Stringdefinition sf --�ַ����Զ�����Ϣ��
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

  /*��ȡnumber value*/
  function get_float_value(p_displayname    in varchar2,
                           p_classnamekeya4 in varchar2,
                           p_IDA3A4         in number) return number is
  
    cursor cur_data is
      select fv.VALUE --����ֵ
        from plm.FLOATvalue      fv, --����������ֵ��Ϣ��
             plm.FLOATdefinition ff --���������Զ����
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

  /*��ȡλ��*/
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

--���BOM��ϵ������
/*
select level, t.*
  from cux_bom_v t
 start with t.parentid = 2227773156
connect by prior t.id = t.parentid*/

end cux_pub_utl;
/
