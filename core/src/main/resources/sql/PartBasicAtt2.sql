select wm.ida2a2, --����Ψһ��ʶ
       wm.NAME, --����
       wm.WTPARTNUMBER                 "number", --���
       wm.CREATESTAMPA2                createtime, --����ʱ��
       wm.MARKFORDELETEA2, --�Ƿ�ɾ��
       wm.MODIFYSTAMPA2                modtime, --�޸�ʱ��
       wp.STATESTATE,
       wm.CLASSNAMEKEYCONTAINERREFEREN, --������������
       wm.IDA3CONTAINERREFERENCE, --"�������������������������������ֵ��PDMLinkProduct/WTLibrary����Ӧ������"
       wl.NAMECONTAINERINFO
  from plm.WTPartMaster wm, WTPart wp, plm.WTLibrary wl
 where wm.ida2a2 = wp.ida2a2
   and wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.inf.library.WTLibrary'
   and wm.ida2a2 = wl.ida2a2(+)
union all
select wm.ida2a2, --����Ψһ��ʶ
       wm.NAME, --����
       wm.WTPARTNUMBER                 "number", --���
       wm.CREATESTAMPA2                createtime, --����ʱ��
       wm.MARKFORDELETEA2, --�Ƿ�ɾ��
       wm.MODIFYSTAMPA2                modtime, --�޸�ʱ��
       wm.CLASSNAMEKEYCONTAINERREFEREN, --������������
       wm.IDA3CONTAINERREFERENCE, --"�������������������������������ֵ��PDMLinkProduct/WTLibrary����Ӧ������"
       pp.NAMECONTAINERINFO
  from plm.WTPartMaster wm, plm.PDMLinkProduct pp--��Ʒ������Ϣ��
 where wm.CLASSNAMEKEYCONTAINERREFEREN = 'wt.pdmlink.PDMLinkProduct'
   and wm.ida2a2 = pp.ida2a2(+);
   
   --һ�������Ӧ����ӱ�
   select cwp.*
     from plm.WTPartMaster cwm, --�Ӳ�����Ϣ����
          plm.WTPart       cwp --�Ӳ�����Ϣ�ӱ�
    where cwm.ida2a2 = cwp.IDA3MASTERREFERENCE
      and cwm.ida2a2 = 2847975;
