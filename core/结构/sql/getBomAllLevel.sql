--获得BOM关系及属性
select level, t.*
  from cux_bom_v t
 start with t.parentid = 348448618
connect by prior t.id = t.parentid;


select level, t.*
  from cux_bom_3252SJ_v t
 start with t.parentid = 348448618
connect by prior t.id = t.parentid;


