# mathcomputing
# sum.R
# mcdonald dot john dot alan at gmail dot com
# since 2017-03-30
# version 2017-04-14
#-----------------------------------------------------------------
setwd('e:/porta/projects/mathcomputing/src/scripts/r')
print(getwd())
source('functions.R')
#-----------------------------------------------------------------
bench.files <- list.files(
  path=data.folder, 
  pattern='sum.*.2017-04-13.tsv', 
  full.names=TRUE)
bench <- NULL
for (bench.file in bench.files) {
  tmp <- read.csv(bench.file,sep='\t',as.is=TRUE)
  bench <- rbind(bench,tmp) }
print(sort(unique(bench$coerce)))
print(sort(unique(bench$data)))
bench$elementtype <- factor(
  bench$elementtype,
  levels=c(
    'byte','Byte','short','Short','int','Integer','long','Long','BigInteger',
    'float','Float','double','Double','Number','Object',
    'BigDecimal','BigFraction','Ratio'))
bench$coerce <- factor(
  bench$coerce,
  levels=c(
    'byte_array','array_of_Byte',
    'short_array','array_of_Short',
    'int_array','array_of_Integer',
    'long_array','array_of_Long',
    'float_array','array_of_Float',
    'double_array','array_of_Double','array_of_Number','array_of_Object',
    'array_of_BigInteger',
    'array_of_BigDecimal',
    'array_of_BigFraction',
    'array_of_Ratio',
    'array_list',
    'vec', 
    'lazy_list'))
bench$data <- factor(
  bench$data,
  levels=c(
    'byte[]',
    'Byte[]',
    'short[]',         
    'Short[]',         
    'int[]', 
    'Integer[]',
    'float[]',
    'Float[]' ,
    'long[]',
    'Long[]' ,
    'double[]',
    'Double[]',
    'Number[]',
    'Object[]',
    'ArrayList',
    'PersistentVector',
    'LazySeq',
    'BigDecimal[]',
    'BigFraction[]' ,
    'BigInteger[]',
    'Ratio[]'))

bench$absError <- abs(bench$truth - bench$value)
bench$relError <- bench$absError / (1 + abs(bench$truth))
summary(bench)
#-----------------------------------------------------------------
plot.folder <- file.path('..','..','..','doc','mthcmptng','figs')
dir.create(plot.folder, showWarnings = FALSE, recursive = TRUE, mode = '0777')
#-----------------------------------------------------------------
v <- 'generator'
x <- 'n'
for (u in c('algorithm', 'data')) {
  for (z in c('algorithm', 'data')) {
    if (u != z) {
      ribbons(bench,u,v,x,'mean',z,'lower.q','upper.q',plot.folder)
      for (y in c('absError','relError')) {
        bench.sub <- bench[
          ((tolower(bench$elementtype) != 'object') & 
              (tolower(bench$elementtype) != 'number') & 
              (tolower(bench$elementtype) != 'byte') & 
              (tolower(bench$elementtype) != 'short') &
              (tolower(bench$elementtype) != 'long') & 
              (tolower(bench$elementtype) != 'int') &
              (tolower(bench$elementtype) != 'integer') &
              (tolower(bench$elementtype) != 'biginteger') &
              (tolower(bench$elementtype) != 'float')),]
        curves(bench.sub,u,v,x,y,z,plot.folder) } } } }
naive <- bench[(bench$algorithm == 'naive'),]
curves(naive,NULL,NULL,x,'sizeof','data',plot.folder) 
#-----------------------------------------------------------------
