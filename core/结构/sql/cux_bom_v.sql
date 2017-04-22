create or replace view cux_bom_v as
select wpl.IDA2A2 linkida2a2, --BOM��ϵΨһ��ʶ
       cwm.ida2a2 childmasterida2a2, --�Ӽ�MasterΨһ��
       wpl.IDA3A5 parentid, --
       cwp.ida2a2 id,
       cwm.WTPARTNUMBER childnum, --�Ӽ����
       cwm.NAME childname, --�Ӽ�����
       case (select count(1)
           from plm.WTPartusageLink pwpl
          where pwpl.IDA3A5 = cwp.ida2a2)
         when 0 then
          'open'
         else
          'closed'
       end state, --�Ƿ����ӽڵ㣨�У�closed,û�У�open��
       case (select count(1)
           from plm.WTPartusageLink pwpl
          where pwpl.IDA3A5 = cwp.ida2a2)
         when 0 then
          'false'
         else
          'true'
       end hasChildren, --�Ƿ����ӽڵ㣨�У�closed,û�У�open��
       case (select count(1)
           from plm.WTPartusageLink pwpl
          where pwpl.IDA3A5 = cwp.ida2a2)
         when 0 then
          'false'
         else
          'true'
       end has_children, --�Ƿ����ӽڵ㣨�У�closed,û�У�open��
       (select count(1)
          from plm.WTPartusageLink pwpl
         where pwpl.IDA3A5 = cwp.ida2a2) total, --�ӽڵ�����
       wpl.VALUEB7 linenum, --�к�
       cux_pub_utl.get_occurrence(p_classnamekeylinkreference => 'wt.part.WTPartUsageLink',
                                  p_ida3linkreference         => wpl.IDA2A2) occurrence, --λ��
       wpl.AMOUNTA7 amount, --����
       wpl.UNITA7 unit, --  ��λ
       cwp.STATESTATE partstate, --״̬
       decode(cwp.STATECHECKOUTINFO, 'c/i', '����', 'c/o', '���') status, --״����c/i���룻c/o���
       cwp.VERSIONIDA2VERSIONINFO || '.' || cwp.ITERATIONIDA2ITERATIONINFO version, --�汾 ��汾��Ϣ.С�汾��Ϣ
       cux_pub_utl.get_string_value(p_displayname    => 'BOM��ϵ����',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) linkweight, --BOM��ϵ����
       cux_pub_utl.get_string_value(p_displayname    => '��ϵ��Դ',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) source, --��ϵ��Դ
       cux_pub_utl.get_string_value(p_displayname    => '�Ƿ������',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) isvirtual, --�Ƿ������
       cux_pub_utl.get_string_value(p_displayname    => '���ȼ�',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) priory, --���ȼ�
       cux_pub_utl.get_string_value(p_displayname    => '�Ƿ���',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) isBoard, --�Ƿ���
       cux_pub_utl.get_string_value(p_displayname    => '�滻��',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) replacement, --�滻��
       cux_pub_utl.get_string_value(p_displayname    => '�Ƿ���ü�',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) isborrow, --�Ƿ���ü�
       cux_pub_utl.get_string_value(p_displayname    => '�Ƿ�ؼ���',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) iskey, --�Ƿ�ؼ���
       cux_pub_utl.get_string_value(p_displayname    => '��ע',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) discription, --��ע
       cux_pub_utl.get_string_value(p_displayname    => '�Ƿ�ѡ���',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) isselected, --�Ƿ�ѡ���,
       cwv.name "view", --��ͼ
       cux_pub_utl.get_string_value(p_displayname    => 'ͼ��',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) material, --���ϱ���
       cux_pub_utl.get_string_value(p_displayname    => 'ͼ��',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) producttype, --��Ʒ����
       cux_pub_utl.get_string_value(p_displayname    => 'ͼ��',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) classification, --��Ʒ����
       cwp.VERSIONIDA2VERSIONINFO sapversion, --SAP�汾
       cux_pub_utl.get_string_value(p_displayname    => 'ͼ��',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) drawingsize, --ͼ��
       cux_pub_utl.get_string_value(p_displayname    => 'ͼֽ����',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) cadtype, --ͼֽ����
       cux_pub_utl.get_string_value(p_displayname    => 'ͼ������',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) drawingtype, --ͼ������
       cux_pub_utl.get_string_value(p_displayname    => '�ͺŹ��',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) specification, --�ͺŹ��
       cux_pub_utl.get_string_value(p_displayname    => '���ϵȼ�',
                                    p_classnamekeya4 => 'wt.part.WTPart',
                                    p_ida3a4         => cwp.ida2a2) marlevel, --���ϵȼ�
       cux_pub_utl.get_float_value(p_displayname    => '���ϵ���',
                                   p_classnamekeya4 => 'wt.part.WTPart',
                                   p_ida3a4         => cwp.ida2a2) weight --���ϵ���

  from plm.WTPartusageLink wpl, --Bom������Ϣ��
       plm.WTPart          pwp, --��������Ϣ�ӱ�
       plm.WTPartMaster    cwm, --�Ӳ�����Ϣ����
       plm.WTPart          cwp, --�Ӳ�����Ϣ�ӱ�
       plm.WTView          cwv --����ͼ��Ϣ��
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
