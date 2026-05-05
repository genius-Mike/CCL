#include <iostream>
#include <fstream>
#include <string>
//#include <cstring>
#include <vector>

using namespace std;
// #define ciao 10;

struct variabile{
    string nome;
    string visibilita;
    string tipo;
};
struct metodo{
    vector<variabile> parametri;
    string nome;
    string tipo;
    string visibilita;
    string valReturn;
};
void BuildSetters(ofstream& cout, vector<variabile> attributi);
void BuildGetters(ofstream& cout, vector<variabile> attributi);
void BuildBuilders(ofstream& cout, string nomeClasse, vector<variabile> attributi);
variabile BuildAttributo(string assegnazioneNomi);
metodo BuildMetodo(string assegnazioneNomi);

string accessoAttributo[3] = {"public", "protected", "private"};
string setAttributo[8] = {"int", "float", "double", "char", "String", "boolean", "ArrayList", "void"};

int main()
{   
    ifstream cin("input.txt");
    ofstream cout("class.java");

    //CREAZIONE AUTOMATICA DI GETTER E SETTER PER ATTRIBUTI DI TIPO PRIVATE E PROTECTED
    vector<variabile> attributi;
    string nomeClasse, assegnazioneNomi, subString;
    int inputTipo, indexAccesso, indexType, strPointer;
    char repliche, charInput;
    variabile varTemp;
    metodo metTemp;
    // Create and open a text file

    // Classe
    getline (cin, assegnazioneNomi);
    cout << "public class " + assegnazioneNomi + "{\n";
    nomeClasse = assegnazioneNomi;
    
    //leggere parametri
    while (getline (cin, assegnazioneNomi)) {
        if(assegnazioneNomi == ""){
            break;
        }
        varTemp = BuildAttributo(assegnazioneNomi); 

        if(varTemp.tipo.substr(0,9) != "ArrayList"){
            attributi.push_back(varTemp);
        }
        
        cout << "\t" + varTemp.visibilita+" "+varTemp.tipo+" "+varTemp.nome+";\n";
    }

    //Costruttori - vuoto e pieno    versione disuso = copia e incolla
    BuildBuilders(cout, nomeClasse, attributi);

    //Getter & Setter
    //set
    BuildSetters(cout, attributi);

    //get
    BuildGetters(cout, attributi);

    //Metodi
    while (getline (cin, assegnazioneNomi)) {
        if(assegnazioneNomi == ""){
            break;
        }
       metTemp = BuildMetodo(assegnazioneNomi); 
      cout << "\t" + metTemp.visibilita+" "+metTemp.tipo+" "+metTemp.nome+"(";



      cout << "){\n";


      if(metTemp.tipo.compare("void") != 0){
        cout << "\t\treturn " + metTemp.valReturn + ";\n";
      }
      cout << "\t}\n";
    }

    cout << "}";
    // Close the file
    cout.close();

    rename("class.java", (nomeClasse +".java").c_str());
    return 0;
}

void BuildSetters(ofstream& cout, vector<variabile> attributi){
    string nomeTemp;
    variabile tmp;
    for (int i = 0; i < attributi.size(); i++)
    {   
        tmp = attributi.at(i);
        nomeTemp = tmp.nome;
        nomeTemp[0] = toupper(nomeTemp[0]);
        cout << "\tpublic void set" + nomeTemp + "(" + tmp.tipo + " " + tmp.nome[0] + "){\n";
        cout << "\t\t" + tmp.nome + " = " + tmp.nome[0] + ";\n";
        cout << "\t}\n";
    }
}
void BuildGetters(ofstream& cout, vector<variabile> attributi){
    string nomeTemp;
    variabile tmp;
    for(int i = 0; i < attributi.size(); i++){
        tmp = attributi.at(i);
        nomeTemp = tmp.nome;
        nomeTemp[0] = toupper(nomeTemp[0]);
        cout <<"\tpublic " + tmp.tipo + " get" + nomeTemp + "(){\n";
        cout << "\t\treturn " + tmp.nome + ";\n";
        cout << "\t}\n";
    }
}

void BuildBuilders(ofstream& cout, string nomeClasse, vector<variabile> attributi){
    vector<char> memNomiCasuali;
    char casualName = 'a';
    variabile tmp;

        cout <<"\tpublic " + nomeClasse + "(";
    for(int i = 0; i < attributi.size(); i++){
        // if(attributi.at(i).tipo.length() >=9 && attributi.at(i).tipo.substr(0,9) == "ArrayList")
        // continue;
        cout << attributi.at(i).tipo + " " + casualName;  
        if(i+1 != attributi.size()){
            cout << ", ";
        }
        memNomiCasuali.push_back(casualName);
        casualName += 1;
    }
    cout << "){\n";
    for(int i = 0; i < attributi.size(); i++){
        if(attributi.at(i).tipo.length() >=9 && attributi.at(i).tipo.substr(0,9) == "ArrayList"){
            i++;
            continue;
        }
        cout << "\t\t" + attributi.at(i).nome + " = " + memNomiCasuali.at(i) + ";\n";  
    }
    cout << "\t}\n";

    cout <<"\tpublic " + nomeClasse + "(){\n";
    for(int i = 0; i < attributi.size(); i++){
        if(attributi.at(i).tipo.length() >=9 && attributi.at(i).tipo.substr(0,9) == "ArrayList")
        continue;
        tmp = attributi.at(i);
        cout << "\t\t" + tmp.nome;
        if(tmp.tipo.compare("int") == 0){
            cout << " = 0;\n";  
        }else if(tmp.tipo.compare("String") == 0){
            cout << " = \"\";\n";  
        }else if(tmp.tipo.compare("float") == 0){
            cout << " = 0;\n";  
        }else if(tmp.tipo.compare("boolean") == 0){
            cout << " = false;\n";  
        }else{
            cout << ";\n"; 
        }
    }
    cout << "\t}\n";
}

variabile BuildAttributo(string assegnazioneNomi){
    variabile memTemp;
    char charInput;
    int indexAccesso, strPointer, indexType;
    string subString;
    charInput = assegnazioneNomi.at(0);
    strPointer = assegnazioneNomi.find(':');
    indexAccesso = 0;
    if(charInput == '+'){
        indexAccesso = 0;
        subString = assegnazioneNomi.substr(strPointer+1, assegnazioneNomi.length());
    }else if(charInput == '#'){
        indexAccesso = 1;
            subString = assegnazioneNomi.substr(strPointer+1, assegnazioneNomi.length());
    }else if(charInput == '-'){
        indexAccesso = 2;
        subString = assegnazioneNomi.substr(strPointer+1, assegnazioneNomi.length());
    }
    memTemp.visibilita = accessoAttributo[indexAccesso];
    
    if(strPointer == 2){ //assegnazioneNomi.at(2) == ':'
        charInput = assegnazioneNomi.at(1);
        switch(charInput){
            case 'i':
                indexType = 0;
                break;
            case 'f':
                indexType = 1;
                break;
            case 'd':
                indexType = 2;
                break;
            case 'c':
                indexType = 3;
                break;
            case 'S':
                indexType = 4;
                break;
            case 'b':
                indexType = 5;
                break;
            default:
                indexType=-1;
                break;
            
        }
        
            memTemp.tipo = setAttributo[indexType];

    }else if(assegnazioneNomi.substr(1,2).compare("AL") == 0){
        subString = assegnazioneNomi.substr(assegnazioneNomi.find('.')+1, strPointer - (assegnazioneNomi.find('.')+1)); //4, ...
            memTemp.tipo = setAttributo[6] + "<" + subString + ">";
    }else{
        subString = assegnazioneNomi.substr(1, strPointer-1);
        memTemp.tipo = subString;
    }

    memTemp.nome = assegnazioneNomi.substr(strPointer+1, assegnazioneNomi.length());
    return memTemp;
}

metodo BuildMetodo(string assegnazioneNomi){
    metodo memTemp;
    char charInput;
    int indexAccesso, strPointer, indexType;
    string subString;
    charInput = assegnazioneNomi.at(0);
    strPointer = assegnazioneNomi.find(':');
    indexAccesso = 0;
    if(charInput == '+'){
        indexAccesso = 0;
        subString = assegnazioneNomi.substr(strPointer+1, assegnazioneNomi.length());
    }else if(charInput == '#'){
        indexAccesso = 1;
            subString = assegnazioneNomi.substr(strPointer+1, assegnazioneNomi.length());
    }else if(charInput == '-'){
        indexAccesso = 2;
        subString = assegnazioneNomi.substr(strPointer+1, assegnazioneNomi.length());
    }
    memTemp.visibilita = accessoAttributo[indexAccesso];
    
    if(strPointer == 2){ //assegnazioneNomi.at(2) == ':'
        charInput = assegnazioneNomi.at(1);
        switch(charInput){
            case 'i':
                indexType = 0;
                memTemp.valReturn = "0";
                break;
            case 'f':
                indexType = 1;
                memTemp.valReturn = "0";
                break;
            case 'd':
                indexType = 2;
                memTemp.valReturn = "0";
                break;
            case 'c':
                indexType = 3;
                memTemp.valReturn = "0";
                break;
            case 'S':
                indexType = 4;
                memTemp.valReturn = "stringa";
                break;
            case 'b':
                indexType = 5;
                memTemp.valReturn = "false";
                break;
            case 'v':
                indexType = 7;
                break;
            default:
                indexType=-1;
                break;
            
        }
        
            memTemp.tipo = setAttributo[indexType];

    }else if(assegnazioneNomi.substr(1,2).compare("AL") == 0){
        subString = assegnazioneNomi.substr(assegnazioneNomi.find('.')+1, strPointer - (assegnazioneNomi.find('.')+1)); //4, ...
            memTemp.tipo = setAttributo[6] + "<" + subString + ">";
    }else{
        subString = assegnazioneNomi.substr(1, strPointer-1);
        memTemp.tipo = subString;
    }

    memTemp.nome = assegnazioneNomi.substr(strPointer+1, assegnazioneNomi.length());
    return memTemp;
}