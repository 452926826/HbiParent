select wm.ida2a2, --����Ψһ��ʶ
       wm.NAME, --����
       wm.WTPARTNUMBER "number", --���
       wm.MARKFORDELETEA2, --�Ƿ�ɾ��
       decode(wp.STATECHECKOUTINFO, 'c/i', '����', 'c/o', '���') status, --״����c/i���룻c/o���
       wv.name "view", --��ͼ
       wp.STATESTATE, --״̬
       wm.MODIFYSTAMPA2 modtime, --�޸�ʱ��
       wm.CREATESTAMPA2 createtime, --����ʱ��
       wl.NAMECONTAINERINFO container, --��������
       wu.name modifyer, --�޸���
       wp.ITERATIONIDA2ITERATIONINFO, --С�汾��Ϣ
       wp.LATESTITERATIONINFO, --��汾���±�ʶ��1��汾����
       wp.VERSIONIDA2VERSIONINFO --��汾��Ϣ
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
union all
select wm.ida2a2, --����Ψһ��ʶ
       wm.NAME, --����
       wm.WTPARTNUMBER "number", --���
       wm.MARKFORDELETEA2, --�Ƿ�ɾ��
       decode(wp.STATECHECKOUTINFO, 'c/i', '����', 'c/o', '���') status, --״����c/i���룻c/o���
       wv.name "view", --��ͼ
       wp.STATESTATE, --״̬
       wm.MODIFYSTAMPA2 modtime, --�޸�ʱ��
       wm.CREATESTAMPA2 createtime, --����ʱ��
       pp.NAMECONTAINERINFO container, --��������
       wu.name modifyer, --�޸���
       wp.ITERATIONIDA2ITERATIONINFO, --С�汾��Ϣ
       wp.LATESTITERATIONINFO, --��汾���±�ʶ��1��汾����
       wp.VERSIONIDA2VERSIONINFO --��汾��Ϣ
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
