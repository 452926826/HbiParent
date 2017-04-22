create or replace view cux_part_basic_att_v as
select wm.ida2a2, --����Ψһ��ʶ
       wp.ida2a2 wp_ida2a2,
       wm.NAME, --����
       wm.WTPARTNUMBER "number", --���
       wm.MARKFORDELETEA2, --�Ƿ�ɾ��
       wp.VERSIONIDA2VERSIONINFO || '.' || wp.ITERATIONIDA2ITERATIONINFO version, --�汾 ��汾��Ϣ.С�汾��Ϣ
       decode(wp.STATECHECKOUTINFO, 'c/i', '����', 'c/o', '���') status, --״����c/i���룻c/o���
       wv.name "view", --��ͼ
       wp.STATESTATE, --״̬
       wm.MODIFYSTAMPA2 modtime, --�޸�ʱ��
       wm.CREATESTAMPA2 createtime, --����ʱ��
       wl.NAMECONTAINERINFO container, --��������
       wu.name modifyer, --�޸���
       wp.LATESTITERATIONINFO --��汾���±�ʶ��1��汾����
  from plm.WTPartMaster wm, --������Ϣ����
       plm.WTPart       wp, --������Ϣ�ӱ�
       plm.WTView       wv, --��ͼ��Ϣ��
       plm.WTLibrary    wl, --�洢����Ϣ��
       plm.WTUser       wu --�û���Ϣ��
 where wm.ida2a2 = wp.IDA3MASTERREFERENCE
   and wp.IDA3VIEW = wv.IDA2A2
   and wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.inf.library.WTLibrary'
   and wm.IDA3CONTAINERREFERENCE = wl.ida2a2
   and wp.IDA3A2OWNERSHIP = wu.IDA2A2
   --and wm.NAME = '�ȹ��ؼ̵���'
   --and wm.WTPARTNUMBER = 'W27233504022'
union all
select wm.ida2a2, --����Ψһ��ʶ
       wp.ida2a2 wp_ida2a2,
       wm.NAME, --����
       wm.WTPARTNUMBER "number", --���
       wm.MARKFORDELETEA2, --�Ƿ�ɾ��
       wp.VERSIONIDA2VERSIONINFO || '.' || wp.ITERATIONIDA2ITERATIONINFO version, --�汾 ��汾��Ϣ.С�汾��Ϣ
       decode(wp.STATECHECKOUTINFO, 'c/i', '����', 'c/o', '���') status, --״����c/i���룻c/o���
       wv.name "view", --��ͼ
       wp.STATESTATE, --״̬
       wm.MODIFYSTAMPA2 modtime, --�޸�ʱ��
       wm.CREATESTAMPA2 createtime, --����ʱ��
       pp.NAMECONTAINERINFO container, --��������
       wu.name modifyer, --�޸���
       wp.LATESTITERATIONINFO --��汾���±�ʶ��1��汾����
  from plm.WTPartMaster   wm, --������Ϣ����
       plm.WTPart         wp, --������Ϣ�ӱ�
       plm.WTView         wv, --��ͼ��Ϣ��
       plm.PDMLinkProduct pp, --��Ʒ������Ϣ��
       plm.WTUser         wu --�û���Ϣ��
 where wm.ida2a2 = wp.IDA3MASTERREFERENCE
   and wp.IDA3VIEW = wv.IDA2A2
   and wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.pdmlink.PDMLinkProduct'
   and wm.IDA3CONTAINERREFERENCE = pp.ida2a2
   and wp.IDA3A2OWNERSHIP = wu.IDA2A2
