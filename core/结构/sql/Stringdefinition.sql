select sf.DISPLAYNAME, --������ʾ��
       sf.NAME, -- ��������
       sf.IDA2A2
  from plm.Stringdefinition sf --�ַ����Զ�����Ϣ��
 where sf.displayname = '���ϵȼ�';

select sv.CLASSNAMEKEYA4, -- ��������
       sv.IDA3A4, --  ��������������
       sv.IDA3A6, --   ��������ַ����Զ�������
       sv.VALUE2, --����ֵ
       sv.IDA2A2
  from plm.Stringvalue sv --�ַ�����ֵ��Ϣ��
;

select sf.DISPLAYNAME, --������ʾ��
       sf.NAME, -- ��������
       sf.IDA2A2,
       sv.CLASSNAMEKEYA4, -- ��������
       sv.IDA3A4, --  ��������������
       sv.IDA3A6, --   ��������ַ����Զ�������
       sv.VALUE2, --����ֵ
       sv.IDA2A2,
       sv.*
  from plm.Stringvalue      sv, --�ַ�����ֵ��Ϣ��
       plm.Stringdefinition sf --�ַ����Զ�����Ϣ��
 where sv.IDA3A6 = sf.IDA2A2
   and sf.displayname = '�ͺŹ��';
