--��ȡ��Ʒ������
select wm.NAME, --����
       wm.WTPARTNUMBER "number", --���
       wtm.IDA2A2, --�ĵ�Ψһ��ʶ
       wtm.WTDOCUMENTNUMBER docnum, --�ĵ����
       wtm.NAME docname, --�ĵ�����
       wu.name modifyer, --�޸���
       wtm.MODIFYSTAMPA2 modtime, --�޸�ʱ��
       decode(wt.STATECHECKOUTINFO, 'c/i', '����', 'c/o', '���') status, --״����c/i���룻c/o���
       wt.STATESTATE state, --״̬
       wl.NAMECONTAINERINFO container, --��������
       wts.DISPLAYNAMEKEY doctype, --�ĵ����� ������ʾ����
       wts.INTHID, --�������� 
       wsl.IDA3A5, --������Ϣ�ӱ�ID
       wsl.IDA2A2 linkida2a2 --�ĵ��벿����ϵΨһ��ʶ
  from plm.WTDocumentMaster       wtm, --�ĵ���Ϣ����
       plm.WTDocument             wt, --�ĵ���Ϣ�ӱ�
       plm.WTLibrary              wl, --�洢����Ϣ��
       plm.WTPARTDESCRIBELINK     wsl, --�������ĵ�������Ϣ��  IDA3B5 --��������ĵ���Ϣ�ӱ����    IDA3A5������벿����Ϣ�ӱ����
       plm.WTTYPEDEFINITIONMASTER wts, --���Ͷ�������
       plm.WTTYPEDEFINITION       wtd, --���Ͷ���ӱ�
       plm.WTUser                 wu, --�û���Ϣ��
       plm.WTPartMaster           wm, --������Ϣ����
       plm.WTPart                 wp --������Ϣ�ӱ�
 where wtm.IDA2A2 = wt.IDA3MASTERREFERENCE
   and wtm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.inf.library.WTLibrary'
   and wtm.IDA3CONTAINERREFERENCE = wl.ida2a2
   and wt.IDA2A2 = wsl.IDA3B5 --���ĵ���Ϣ�ӱ���� 
   and wt.IDA2TYPEDEFINITIONREFERENCE = wtd.IDA2A2 --����������Ͷ���ӱ����
   and wts.IDA2A2 = wtd.IDA3MASTERREFERENCE
   and wt.IDA3A2OWNERSHIP = wu.IDA2A2
   and wm.ida2a2 = wp.IDA3MASTERREFERENCE
   and wsl.IDA3A5 = wp.ida2a2 --1182744070 --�벿����Ϣ�ӱ����
   and wm.ida2a2 = 2004939
union all
select wm.NAME, --����
       wm.WTPARTNUMBER "number", --���
       wtm.IDA2A2, --�ĵ�Ψһ��ʶ
       wtm.WTDOCUMENTNUMBER docnum, --�ĵ����
       wtm.NAME docname, --�ĵ�����
       wu.name modifyer, --�޸���
       wtm.MODIFYSTAMPA2 modtime, --�޸�ʱ��
       decode(wt.STATECHECKOUTINFO, 'c/i', '����', 'c/o', '���') status, --״����c/i���룻c/o���
       wt.STATESTATE state, --״̬
       pp.NAMECONTAINERINFO container, --��������
       wts.DISPLAYNAMEKEY doctype, --�ĵ����� ������ʾ����
       wts.INTHID, --�������� 
       wsl.IDA3A5, --������Ϣ�ӱ�ID
       wsl.IDA2A2 linkida2a2 --�ĵ��벿����ϵΨһ��ʶ
  from plm.WTDocumentMaster       wtm, --�ĵ���Ϣ����
       plm.WTDocument             wt, --�ĵ���Ϣ�ӱ�
       plm.PDMLinkProduct         pp, --��Ʒ������Ϣ��
       plm.WTPARTDESCRIBELINK     wsl, --�������ĵ�������Ϣ��  IDA3B5 --��������ĵ���Ϣ�ӱ����    IDA3A5������벿����Ϣ�ӱ����
       plm.WTTYPEDEFINITIONMASTER wts, --���Ͷ�������
       plm.WTTYPEDEFINITION       wtd, --���Ͷ���ӱ�
       plm.WTUser                 wu, --�û���Ϣ��
       plm.WTPartMaster           wm, --������Ϣ����
       plm.WTPart                 wp --������Ϣ�ӱ�
 where wtm.IDA2A2 = wt.IDA3MASTERREFERENCE
   and wtm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.pdmlink.PDMLinkProduct'
   and wm.IDA3CONTAINERREFERENCE = pp.ida2a2
   and wt.IDA2A2 = wsl.IDA3B5 --���ĵ���Ϣ�ӱ���� 
   and wt.IDA2TYPEDEFINITIONREFERENCE = wtd.IDA2A2 --����������Ͷ���ӱ����
   and wts.IDA2A2 = wtd.IDA3MASTERREFERENCE
   and wt.IDA3A2OWNERSHIP = wu.IDA2A2
   and wm.ida2a2 = wp.IDA3MASTERREFERENCE
   and wsl.IDA3A5 = wp.ida2a2 --1182744070 --�벿����Ϣ�ӱ����
   --and wm.ida2a2 = 2004939
   and wm.WTPARTNUMBER = 'TE020-351001'
