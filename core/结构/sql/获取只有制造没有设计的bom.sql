select wpl.IDA2A2 linkida2a2, --BOM��ϵΨһ��ʶ
       cwm.ida2a2 childmasterida2a2, --�Ӽ�MasterΨһ��
       wpl.IDA3A5 parentid, --
       cwp.ida2a2 id,
       cwm.WTPARTNUMBER childnum, --�Ӽ����
       cwm.NAME childname, --�Ӽ�����
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
       cwv.name "view", --��ͼ
       cwp.IDA3VIEW

  from plm.WTPartusageLink wpl, --Bom������Ϣ��
       plm.WTPart          pwp, --��������Ϣ�ӱ�
       plm.WTPartMaster    cwm, --�Ӳ�����Ϣ����
       plm.WTPart          cwp, --�Ӳ�����Ϣ�ӱ�
       plm.WTView          cwv --����ͼ��Ϣ��
 where wpl.IDA3A5 = pwp.IDA2A2
   and wpl.IDA3B5 = cwm.IDA2A2
   and cwm.ida2a2 = cwp.IDA3MASTERREFERENCE
   and cwp.IDA3VIEW = cwv.IDA2A2
   and pwp.IDA3VIEW = 3252
      --and wpl.IDA3A5 = 7540438
      --and cwm.ida2a2 = 47038093
   and not exists (select null
          from plm.WTPart cwpn
         where cwm.ida2a2 = cwpn.IDA3MASTERREFERENCE
           and cwpn.IDA3VIEW = 3252 --�����ͼ
        )
   and rownum = 1;
