--���BOM��ϵ������
select wpl.IDA2A2              linkida2a2, --BOM��ϵΨһ��ʶ 
       null                    childmasterida2a2, --�Ӽ�MasterΨһ��
       wpl.IDA3B5              "id", --�Ӽ�Ψһ��ʶ "������벿����Ϣ���������Bom����ڵ�"
       pwp.IDA3MASTERREFERENCE parentid, --����Ψһ��ʶ
       --wpl.IDA3A5, --"������벿����Ϣ�ӱ������Bom����ڵ�"
       null state, --�Ƿ����ӽڵ㣨�У�closed,û�У�open��
       null total, --�ӽڵ�����
       wpl.VALUEB7 linenum, --�к�
       null occurrence, --λ��
       wpl.AMOUNTA7 amount, --����
       wpl.UNITA7 unit, --  ��λ
       cwm.WTPARTNUMBER childnum, --�Ӽ����
       cwm.NAME childname, --�Ӽ�����
       cwp.STATESTATE partstate, --״̬
       decode(cwp.STATECHECKOUTINFO, 'c/i', '����', 'c/o', '���') status, --״����c/i���룻c/o���
       null "version", --�汾
       null linkweight, --BOM��ϵ����
       null "source", --��ϵ��Դ
       null isvirtual, --�Ƿ������
       null priory, --���ȼ�
       null isBoard, --�Ƿ���
       null replacement, --�滻��
       null isborrow, --�Ƿ���ü�
       null iskey, --�Ƿ�ؼ���
       null discription, --��ע
       null isselected, --�Ƿ�ѡ���,
       cwv.name "view", --��ͼ
       null material, --���ϱ���
       null producttype, --��Ʒ����
       null classification, --��Ʒ����
       null sapversion, --SAP�汾
       null drawingsize, --ͼ��
       null cadtype, --ͼֽ����
       null drawingtype, --ͼ������
       null "specification", --�ͺŹ��
       null marlevel, --���ϵȼ�
       null weight --���ϵ���

  from plm.WTPartusageLink wpl, --Bom������Ϣ��
       plm.WTPart          pwp, --��������Ϣ�ӱ�
       plm.WTPartMaster    cwm, --�Ӳ�����Ϣ����
       plm.WTPart          cwp, --�Ӳ�����Ϣ�ӱ�
       plm.WTView          cwv --����ͼ��Ϣ��
 where wpl.IDA3A5 = pwp.IDA2A2
   and wpl.IDA3B5 = cwm.IDA2A2
   and cwm.ida2a2 = cwp.IDA3MASTERREFERENCE
   and cwp.IDA3VIEW = cwv.IDA2A2
      
   and wpl.IDA2A2 = 7626371;
