select ff.DISPLAYNAME, --������ʾ��
       ff.NAME, -- ��������
       ff.IDA2A2
  from plm.FLOATdefinition ff --���������Զ����
 where ff.displayname = '���ϵȼ�';

select fv.CLASSNAMEKEYA5, -- ��������
       fv.IDA3A5, --  ��������������
       fv.IDA3A6, --   ������븡�������Զ�������
       fv.VALUE, --����ֵ
       fv.IDA2A2
  from plm.FLOATvalue fv --����������ֵ��Ϣ��
;

select ff.DISPLAYNAME, --������ʾ��
       ff.NAME, -- ��������
       ff.IDA2A2,
       fv.CLASSNAMEKEYA5, -- ��������
       fv.IDA3A5, --  ��������������
       fv.IDA3A6, --   ������븡�������Զ�������
       fv.VALUE, --����ֵ
       fv.IDA2A2,
       fv.*
  from plm.FLOATvalue      fv, --����������ֵ��Ϣ��
       plm.FLOATdefinition ff --���������Զ����
 where fv.IDA3A6 = ff.IDA2A2
   and ff.displayname = '���ϵ���'
   and fv.CLASSNAMEKEYA5 is not null;
