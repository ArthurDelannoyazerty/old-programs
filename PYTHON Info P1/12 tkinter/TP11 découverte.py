from tkinter import*

#ex 1
fen = Tk();
cv = Canvas(fen,width=300, height=300, bg='black')
cv.pack()
cv.create_rectangle(50,50,100,150,fill='green',outline='yellow',width=3)#(x,y,l,L...)
cv.create_oval(100,100,250,250,fill='red',outline='yellow',width=3)#(x,y,l,L...) cercle inscrit dans un carré
fen.mainloop()
