select cwm.ida2a2 childmasterida2a2, --�Ӽ�MasterΨһ��
       cwp.ida2a2 id,
       cwm.WTPARTNUMBER childnum, --�Ӽ����
       cwm.NAME childname, --�Ӽ�����
       cwp.STATESTATE partstate, --״̬
       decode(cwp.STATECHECKOUTINFO, 'c/i', '����', 'c/o', '���') status, --״����c/i���룻c/o���
       cwp.VERSIONIDA2VERSIONINFO || '.' || cwp.ITERATIONIDA2ITERATIONINFO version, --�汾 ��汾��Ϣ.С�汾��Ϣ
       cwv.name "view", --��ͼ
       cwp.IDA3VIEW

  from plm.WTPartMaster cwm, --�Ӳ�����Ϣ����
       plm.WTPart       cwp, --�Ӳ�����Ϣ�ӱ�
       plm.WTView       cwv --����ͼ��Ϣ��
 where cwm.ida2a2 = cwp.IDA3MASTERREFERENCE
   and cwp.IDA3VIEW = cwv.IDA2A2
 -- and cwp.ida2a2 = 210638092
 --TE9999000000
   and cwm.WTPARTNUMBER ='6102H04-0000000'
   --and cwp.VERSIONIDA2VERSIONINFO || '.' || cwp.ITERATIONIDA2ITERATIONINFO ='A.B.1'
   ;
