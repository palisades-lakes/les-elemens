# mathcomputing
# bench.R
# mcdonald dot john dot alan at gmail dot com
# 2017-04-01
#-----------------------------------------------------------------
# libraries
#-----------------------------------------------------------------
require("ggplot2")
#-----------------------------------------------------------------
# global vars
#-----------------------------------------------------------------

data.folder <- file.path("..","..","..","data")

#-----------------------------------------------------------------
plot.file <- function (folder,u,v,x,y,z,ext) {
  fname <- gsub('(-)+','-',paste(u,v,x,y,z,sep='-'))
  file.path(folder,paste(fname,ext,sep='.')) } 
#-----------------------------------------------------------------
ribbons <- function (data,u,v,x,y,z,ymin,ymax,folder) {
  p <- ggplot(
      data=data, 
      aes_string(x=x, y=y, fill=z, color=z, linetype=z, 
        ymin=ymin, ymax=ymax)) + 
    geom_point() + 
    geom_line() + 
    geom_ribbon(alpha=0.5) + 
    scale_x_log10() + 
    scale_y_log10() + 
    facet_grid(paste(v,'~',u)); 
  ggsave(p + theme_bw(), 
    device='png', 
    file=plot.file(folder,u,v,x,y,z,'ribbons.png'), 
    width=24, 
    height=6, 
    units='cm', 
    dpi=300)}
#-----------------------------------------------------------------
curves <- function (data,u,v,x,y,z,folder) {
  p <- ggplot(
      data=data, 
      aes_string(x=x, y=y, fill=z, color=z, linetype=z)) + 
    geom_point() + 
    geom_line() + 
    scale_x_log10() + 
    scale_y_continuous(trans='log1p')
  if (! (is.null(u) | is.null(v))) {
    p <- p + facet_grid(paste(v,'~',u)) 
    ggsave(p + theme_bw(), 
      device='png', 
      file=plot.file(folder,u,v,x,y,z,'curves.png'), 
      width=24, 
      height=6, 
      units='cm', 
      dpi=300) 
  } else {
    ggsave(p + theme_bw(), 
      device='png', 
      file=plot.file(folder,v,v,x,y,z,'curves.png'), 
      width=24, 
      height=12, 
      units='cm', 
      dpi=300) } }
#-----------------------------------------------------------------
