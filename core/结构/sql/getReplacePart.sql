--��ȡ��Ʒ�滻����
CREATE OR REPLACE VIEW POC_REPLACE_PART_V AS
select wps.IDA3A5           IDA2A2, --BOM��ϵΨһ��ʶ
       wm.NAME, --�滻��������
       wm.WTPARTNUMBER      "number", --�滻���ϱ��
       wl.NAMECONTAINERINFO container --��������
  from plm.WTPARTSUBSTITUTELINK wps, --��Ʒ�滻��ϵ��Ϣ��  IDA3A5�������Bom������Ϣ�����
       plm.WTPartMaster         wm, --������Ϣ����
       plm.WTLibrary            wl --�洢����Ϣ��
--,plm.WTPartusageLink      wpl --Bom������Ϣ��
 where wps.IDA3B5 = wm.ida2a2
   and wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.inf.library.WTLibrary'
   and wm.IDA3CONTAINERREFERENCE = wl.ida2a2
   and wps.IDA3A5 = 106822500 -- wpl.IDA2A2
union all
select wps.IDA3A5           IDA2A2, --BOM��ϵΨһ��ʶ
       wm.NAME, --�滻��������
       wm.WTPARTNUMBER      "number", --�滻���ϱ��
       pp.NAMECONTAINERINFO container --��������
  from plm.WTPARTSUBSTITUTELINK wps, --��Ʒ�滻��ϵ��Ϣ��  IDA3A5�������Bom������Ϣ�����
       plm.WTPartMaster         wm, --������Ϣ����
       plm.PDMLinkProduct       pp --��Ʒ������Ϣ��
--,plm.WTPartusageLink      wpl --Bom������Ϣ��
 where wps.IDA3B5 = wm.ida2a2
   and wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.pdmlink.PDMLinkProduct'
   and wm.IDA3CONTAINERREFERENCE = pp.ida2a2
   and wps.IDA3A5 = 2179659580 -- wpl.IDA2A2
